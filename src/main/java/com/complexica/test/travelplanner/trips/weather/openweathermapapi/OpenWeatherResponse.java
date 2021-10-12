package com.complexica.test.travelplanner.trips.weather.openweathermapapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonInclude(Include.NON_EMPTY)
public class OpenWeatherResponse {

  @JsonProperty("city")
  private City city;

  @JsonProperty("list")
  private List<Weather> weatherList;

  public OpenWeatherResponse() {
  }

  public OpenWeatherResponse(City city,
      List<Weather> list) {
    this.city = city;
    this.weatherList = list;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public List<Weather> getWeatherList() {
    return weatherList;
  }

  public void setWeatherList(List<Weather> weatherList) {
    this.weatherList = weatherList;
  }

  @Override
  public String toString() {
    return "OpenWeatherResponse{" +
        "city=" + city +
        ", weatherList=" + weatherList +
        '}';
  }
}
