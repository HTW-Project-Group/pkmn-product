package de.htwberlin.port.adapter;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class PokemonApiClient {

  private static final String POKEMON_API_URI = "https://api.pokemontcg.io/v2";
  private static final String API_KEY = "6b1ae550-6920-4da1-9d08-bbe4ded90082";
  private final WebClient apiClient;

  public PokemonApiClient() {
    apiClient =
        WebClient.builder().baseUrl(POKEMON_API_URI).defaultHeader("X-Api-Key", API_KEY).build();
  }

  public WebClient getApiClient() {
    return apiClient;
  }
}
