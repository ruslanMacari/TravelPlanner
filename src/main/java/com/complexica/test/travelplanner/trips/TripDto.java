package com.complexica.test.travelplanner.trips;

import java.time.LocalDate;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class TripDto {

  @NotBlank
  private String city;

  @FutureOrPresent
  @DateTimeFormat(iso = ISO.DATE)
  private LocalDate date;

  public TripDto(LocalDate date) {
    this.date = date;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  @Override
  public String toString() {
    return "TripDto{" +
        "city='" + city + '\'' +
        ", date=" + date +
        '}';
  }
}
