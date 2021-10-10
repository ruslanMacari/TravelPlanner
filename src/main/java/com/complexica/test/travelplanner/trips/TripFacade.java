package com.complexica.test.travelplanner.trips;

import com.complexica.test.travelplanner.trips.ForecastDto.ForecastDtoBuilder;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TripFacade {

  public List<ForecastDto> getForecasts() {
    List<ForecastDto> forecasts = Arrays.asList(
        new ForecastDtoBuilder()
            .city("London")
            .country("UK")
            .temp(20)
            .clouds(50)
            .createForecastDto(),
        new ForecastDtoBuilder()
            .city("Moscow")
            .country("RU")
            .temp(15)
            .clouds(20)
            .createForecastDto());
    return forecasts;
  }
}
