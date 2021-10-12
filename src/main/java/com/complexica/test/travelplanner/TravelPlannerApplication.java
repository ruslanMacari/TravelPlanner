package com.complexica.test.travelplanner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableCaching
public class TravelPlannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelPlannerApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(@Value("openWeatherMapUri") String openWeatherMapUri) {
		return new RestTemplateBuilder()
				.rootUri(openWeatherMapUri)
				.build();
	}

	@Bean
	public WebClient openWeatherMapWebClient(
			@Value("${openWeatherMapBaseUrl}") String todoBaseUrl,
			WebClient.Builder webClientBuilder) {
		return webClientBuilder
				.baseUrl(todoBaseUrl)
				.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}

}
