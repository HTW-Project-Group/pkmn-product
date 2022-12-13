package de.htwberlin.core.domain.service;

import de.htwberlin.core.domain.model.Pokemon;
import de.htwberlin.port.adapter.PokemonApiClient;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public class PokemonService extends ApiService implements IPokemonService {

  private static final String POKEMON_CARDS = "/cards/";

  public PokemonService(PokemonApiClient apiClient) {
    super(apiClient);
  }

  @Getter
  @Setter
  @NoArgsConstructor
  public static class PokemonData implements Serializable {
    Pokemon data;
  }

  @Override
  public Optional<Pokemon> findPokemonById(String id) {
    return getRestObjectFromUri(POKEMON_CARDS + id, PokemonData.class).map(PokemonData::getData);
  }

  @Override
  public List<Pokemon> findPokemonByName(String type) {
    return null;
  }

  @Override
  public List<Pokemon> findPokemonByType(String type) {
    return null;
  }

  @Override
  public List<Pokemon> findPokemonByAttackName(String attackName) {
    return null;
  }

  @Override
  public List<Pokemon> findPokemonByWeakness(String weakness) {
    return null;
  }

  @Override
  public List<Pokemon> findPokemonByResistance(String weakness) {
    return null;
  }

  @Override
  public List<Pokemon> findPokemonBySetName(String setName) {
    return null;
  }

  @Override
  public List<Pokemon> findPokemonByRarity(String setName) {
    return null;
  }
}
