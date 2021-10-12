package com.complexica.test.travelplanner;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static org.assertj.core.api.BDDAssertions.then;

import com.complexica.test.travelplanner.common.WireMockInitializer;
import com.complexica.test.travelplanner.trips.weather.openweathermapapi.OpenWeatherMapApi;
import com.complexica.test.travelplanner.trips.weather.openweathermapapi.OpenWeatherResponse;
import com.github.tomakehurst.wiremock.WireMockServer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {WireMockInitializer.class})
public class OpenWeatherMapApiTest {

  @Autowired
  private OpenWeatherMapApi openWeatherMapApi;

  @Autowired
  private WireMockServer wireMockServer;

  private @Value("${openWeatherMapAppid}")
  String appid;

  @AfterEach
  public void afterEach() {
    this.wireMockServer.resetAll();
  }

  @Test
  void getForecastFor_givenStubForRequest_shouldReturnOpenWeatherResponse() throws IOException {
    //given:
    String response = new String(
        Files.readAllBytes(Paths.get("src\\test\\resources\\OpenWeatherMapResponse.json")));
    this.wireMockServer.stubFor(get("/forecast?q=London&appid=" + appid + "&units=metric")
        .willReturn(aResponse()
            .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .withBody(response)));
    //when:
    OpenWeatherResponse actual = openWeatherMapApi.getForecastFor("London");
    //then:
    then(actual.getCity().getName()).isEqualTo("London");
    then(actual.getCity().getCountry()).isEqualTo("GB");
    then(actual.getWeatherList().size()).isEqualTo(2);
    then(actual.getWeatherList().get(0).getDateTime()).isEqualTo(
        LocalDateTime.of(LocalDate.of(2021, 10, 10), LocalTime.of(21, 0, 0)));
    then(actual.getWeatherList().get(0).getTemperature().getValue()).isEqualTo(12.69);
    then(actual.getWeatherList().get(0).getClouds().getValue()).isEqualTo(61);
  }
}
