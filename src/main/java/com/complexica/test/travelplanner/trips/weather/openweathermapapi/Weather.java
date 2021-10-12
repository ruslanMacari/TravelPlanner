package com.complexica.test.travelplanner.trips.weather.openweathermapapi;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import java.time.LocalDateTime;
import java.time.LocalTime;

@JsonInclude(Include.NON_EMPTY)
public class Weather {

  @JsonProperty("dt_txt")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime dateTime;

  @JsonProperty("main")
  private Temperature temperature;

  private Clouds clouds;

  public Weather() {
  }

  public Temperature getTemperature() {
    return temperature;
  }

  public void setTemperature(Temperature temperature) {
    this.temperature = temperature;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public void setDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }

  @Override
  public String toString() {
    return "Weather{" +
        "dateTime=" + dateTime +
        ", temperature=" + temperature +
        '}';
  }

  public Clouds getClouds() {
    return clouds;
  }

  public void setClouds(
      Clouds clouds) {
    this.clouds = clouds;
  }

  public boolean isBetween12pmAnd6pm() {
    return dateTime != null
        && dateTime.toLocalTime().isAfter(LocalTime.of(12, 0))
        && dateTime.toLocalTime().isBefore(LocalTime.of(19, 0));
  }

  public static class Clouds {

    @JsonProperty("all")
    private int value;

    public Clouds() {
    }

    public Clouds(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }

    public void setValue(int value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return "Clouds{" +
          "value=" + value +
          '}';
    }
  }
}
