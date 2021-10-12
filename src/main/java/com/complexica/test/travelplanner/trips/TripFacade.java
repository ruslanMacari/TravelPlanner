package com.complexica.test.travelplanner.trips;

import com.complexica.test.travelplanner.trips.weather.Forecast;
import com.complexica.test.travelplanner.trips.weather.ForecastDto;
import com.complexica.test.travelplanner.trips.weather.ForecastDto.ForecastDtoBuilder;
import com.complexica.test.travelplanner.trips.weather.ForecastService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class TripFacade {

  private final ForecastService forecastService;

  public TripFacade(ForecastService forecastService) {
    this.forecastService = forecastService;
  }

  public List<ForecastDto> getAllForecasts() {
    return toDto(forecastService.getAllForecasts());
  }

  private List<ForecastDto> toDto(List<Forecast> forecasts) {
    return forecasts.stream()
        .map(this::toDto)
        .collect(Collectors.toList());
  }

  private ForecastDto toDto(Forecast forecast) {
    return new ForecastDtoBuilder()
        .city(forecast.getCity())
        .clouds(forecast.getClouds())
        .country(forecast.getCountry())
        .temp(forecast.getTemperature())
        .dateTime(forecast.getDateTime())
        .createForecastDto();
  }

  public void addForecast(TripDto tripDto) {
    forecastService.addForecastFor(tripDto);
  }
}
