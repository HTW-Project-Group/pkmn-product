package de.htwberlin.core.domain.service;

import de.htwberlin.port.adapter.PokemonApiClient;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ApiService {

  private final PokemonApiClient apiClient;

  public <T> Optional<T> getRestObjectFromUri(String uri, Class<T> responseType) {
    try {
      return Optional.ofNullable(
          apiClient.getApiClient().get().uri(uri).retrieve().bodyToMono(responseType).block());
    } catch (Exception e) {
      log.error(e.getMessage());
      return Optional.empty();
    }
  }
}
