package com.complexica.test.travelplanner.trips.weather.openweathermapapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_EMPTY)
public class Temperature {

  @JsonProperty("temp")
  private double value;

  public Temperature() {
  }

  public Temperature(double value) {
    this.value = value;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "Temperature{" +
        "value=" + value +
        '}';
  }
}
