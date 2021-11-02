package travelplanner.trips.weather.openweathermapapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class OpenWeatherMapApi {

  private final WebClient webClient;
  @Value("${openWeatherMapAppid}")
  private String appid;

  public OpenWeatherMapApi(WebClient webClient) {
    this.webClient = webClient;
  }

  public OpenWeatherResponse getForecastFor(String city) {
    return this.webClient
        .get()
        .uri(UriComponentsBuilder.fromPath("/forecast")
            .queryParam("q", city)
            .queryParam("appid", appid)
            .queryParam("units", "metric").toUriString())
        .retrieve()
        .bodyToMono(OpenWeatherResponse.class)
        .block();
  }
}
