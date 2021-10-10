package com.complexica.test.travelplanner.trips;

public class ForecastDto {

  private final String city;
  private final String country;
  private final int temp;
  private final int clouds;

  private ForecastDto(String city, String country, int temp, int clouds) {
    this.city = city;
    this.country = country;
    this.temp = temp;
    this.clouds = clouds;
  }

  public String getCity() {
    return city;
  }

  public String getCountry() {
    return country;
  }

  public int getTemp() {
    return temp;
  }

  public int getClouds() {
    return clouds;
  }

  @Override
  public String toString() {
    return "ForecastDto{" +
        "city='" + city + '\'' +
        ", country='" + country + '\'' +
        ", temp=" + temp +
        ", clouds=" + clouds +
        '}';
  }

  public static class ForecastDtoBuilder {

    private String city;
    private String country;
    private int temp;
    private int clouds;

    public ForecastDtoBuilder city(String city) {
      this.city = city;
      return this;
    }

    public ForecastDtoBuilder country(String country) {
      this.country = country;
      return this;
    }

    public ForecastDtoBuilder temp(int temp) {
      this.temp = temp;
      return this;
    }

    public ForecastDtoBuilder clouds(int clouds) {
      this.clouds = clouds;
      return this;
    }

    public ForecastDto createForecastDto() {
      return new ForecastDto(city, country, temp, clouds);
    }
  }
}
