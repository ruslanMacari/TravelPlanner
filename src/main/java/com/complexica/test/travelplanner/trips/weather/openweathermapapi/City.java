package com.complexica.test.travelplanner.trips.weather.openweathermapapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_EMPTY)
public class City {

  @JsonProperty("name")
  private String name;
  @JsonProperty("country")
  private String country;
  @JsonProperty("id")
  private int id;

  public City(String name, String country, int id) {
    this.name = name;
    this.country = country;
    this.id = id;
  }

  public City() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "City{" +
        "name='" + name + '\'' +
        ", country='" + country + '\'' +
        ", id=" + id +
        '}';
  }
}
