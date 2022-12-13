package de.htwberlin.core.domain.service;

import static org.assertj.core.api.Assertions.assertThat;

import de.htwberlin.port.adapter.PokemonApiClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PokemonServiceTest {

  private IPokemonService pokemonService;

  @BeforeEach
  void setUp() {
    PokemonApiClient apiClient = new PokemonApiClient();
    pokemonService = new PokemonService(apiClient);
  }

  @Test
  void findPokemonById() {
    // given
    final var pokemonId = "basep-1";

    // when
    var result = pokemonService.findPokemonById(pokemonId);

    // then
    assertThat(result).isPresent();
    var pokemon = result.get();
    assertThat(pokemon.getId()).isEqualTo(pokemonId);
  }

  @Test
  void findPokemonByIdEmpty() {
    // given + when
    var result = pokemonService.findPokemonById("quatsch");

    // then
    assertThat(result).isEmpty();
  }

  @Test
  void findPokemonByName() {}

  @Test
  void findPokemonByType() {}

  @Test
  void findPokemonByAttackName() {}

  @Test
  void findPokemonByWeakness() {}

  @Test
  void findPokemonByResistance() {}

  @Test
  void findPokemonBySetName() {}

  @Test
  void findPokemonByRarity() {}
}
