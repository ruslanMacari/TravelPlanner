package com.complexica.test.travelplanner.trips.weather;

import com.complexica.test.travelplanner.trips.TripDto;
import com.complexica.test.travelplanner.trips.weather.openweathermapapi.OpenWeatherMapApi;
import com.complexica.test.travelplanner.trips.weather.openweathermapapi.OpenWeatherResponse;
import com.complexica.test.travelplanner.trips.weather.openweathermapapi.Weather;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class ForecastService {

  private final ForecastRepository repository;
  private final OpenWeatherMapApi openWeatherMapApiMock;
  private final CacheManager cacheManager;

  public ForecastService(ForecastRepository repository, OpenWeatherMapApi openWeatherMapApiMock,
      CacheManager cacheManager) {
    this.repository = repository;
    this.openWeatherMapApiMock = openWeatherMapApiMock;
    this.cacheManager = cacheManager;
  }

  @Transactional
  public void addForecastFor(TripDto tripDto) {
    OpenWeatherResponse response = openWeatherMapApiMock.getForecastFor(tripDto.getCity());
    if (response.getWeatherList() != null) {
      List<Forecast> forecasts = new ArrayList<>();
      for (Weather weather : response.getWeatherList()) {
        if (weather == null || !weather.isBetween12pmAnd6pm()) {
          continue;
        }
        Forecast forecast = new Forecast();
        forecast.setCity(tripDto.getCity());
        if (weather.getClouds() != null) {
          forecast.setClouds(weather.getClouds().getValue());
        }
        if (response.getCity() != null) {
          forecast.setCountry(response.getCity().getCountry());
          forecast.setCityId(response.getCity().getId());
        }
        if (weather.getTemperature() != null) {
          forecast.setTemperature(weather.getTemperature().getValue());
        }
        forecast.setDateTime(weather.getDateTime());
        if (!isInCache(forecast)) {
          forecasts.add(forecast);
        }
      }
      if (!forecasts.isEmpty()) {
        repository.saveAllAndFlush(forecasts);
      }
    }
  }

  private boolean isInCache(Forecast forecast) {
    Cache cache = cacheManager.getCache("forecasts");
    if (cache == null) {
      return false;
    }
    String key = "" + forecast.getCityId() + forecast.getDateTime();
    if (cache.get(key) == null) {
      cache.putIfAbsent(key, forecast);
      return false;
    }
    return true;
  }

  public List<Forecast> getAllForecasts() {
    return repository.findAll();
  }
}
