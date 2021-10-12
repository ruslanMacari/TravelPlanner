package com.complexica.test.travelplanner.trips.weather;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "forecasts")
public class Forecast {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;
  @Column(name = "city", nullable = false)
  private String city;
  @Column(name = "city_id")
  private int cityId;
  @Column(name = "country")
  private String country;
  @Column(name = "temperature")
  private double temperature;
  @Column(name = "clouds")
  private int clouds;
  @Column(name = "date")
  private LocalDateTime dateTime;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public double getTemperature() {
    return temperature;
  }

  public void setTemperature(double temperature) {
    this.temperature = temperature;
  }

  public int getClouds() {
    return clouds;
  }

  public void setClouds(int clouds) {
    this.clouds = clouds;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public void setDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }

  @Override
  public String toString() {
    return "Forecast{" +
        "id=" + id +
        ", city='" + city + '\'' +
        ", country='" + country + '\'' +
        ", temperature=" + temperature +
        ", clouds=" + clouds +
        ", dateTime=" + dateTime +
        '}';
  }

  public int getCityId() {
    return cityId;
  }

  public void setCityId(int cityId) {
    this.cityId = cityId;
  }
}
