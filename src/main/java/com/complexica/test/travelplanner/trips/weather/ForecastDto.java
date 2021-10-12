package com.complexica.test.travelplanner.trips.weather;

import java.time.LocalDateTime;

public class ForecastDto {

  private final String city;
  private final String country;
  private final double temp;
  private final int clouds;
  private final LocalDateTime dateTime;

  private ForecastDto(String city, String country, double temp, int clouds,
      LocalDateTime dateTime) {
    this.city = city;
    this.country = country;
    this.temp = temp;
    this.clouds = clouds;
    this.dateTime = dateTime;
  }

  public String getCity() {
    return city;
  }

  public String getCountry() {
    return country;
  }

  public double getTemp() {
    return temp;
  }

  public int getClouds() {
    return clouds;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  @Override
  public String toString() {
    return "ForecastDto{" +
        "city='" + city + '\'' +
        ", country='" + country + '\'' +
        ", temp=" + temp +
        ", clouds=" + clouds +
        ", dateTime=" + dateTime +
        '}';
  }

  public static class ForecastDtoBuilder {

    private String city;
    private String country;
    private double temp;
    private int clouds;
    private LocalDateTime dateTime;

    public ForecastDtoBuilder city(String city) {
      this.city = city;
      return this;
    }

    public ForecastDtoBuilder country(String country) {
      this.country = country;
      return this;
    }

    public ForecastDtoBuilder temp(double temp) {
      this.temp = temp;
      return this;
    }

    public ForecastDtoBuilder clouds(int clouds) {
      this.clouds = clouds;
      return this;
    }

    public ForecastDtoBuilder dateTime(LocalDateTime dateTime) {
      this.dateTime = dateTime;
      return this;
    }

    public ForecastDto createForecastDto() {
      return new ForecastDto(city, country, temp, clouds, dateTime);
    }
  }
}
