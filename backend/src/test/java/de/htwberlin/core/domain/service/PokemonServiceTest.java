package de.htwberlin.core.domain.service;

import static org.assertj.core.api.Assertions.assertThat;

import de.htwberlin.core.domain.model.Pokemon;
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
    // given
    final var pokemonId = "quatsch";

    // when
    var result = pokemonService.findPokemonById(pokemonId);

    // then
    assertThat(result).isEmpty();
  }

  @Test
  void findPokemonByName() {
    // given
    final var pokemonName = "charizard";

    // when
    var result = pokemonService.findPokemonByName(pokemonName);

    // then
    assertThat(result).isNotEmpty();
    result.forEach(pokemon -> assertThat(pokemon.getName().toLowerCase()).contains(pokemonName));
  }

  @Test
  void findPokemonByNameEmpty() {
    // given
    final var pokemonName = "";

    // when
    var result = pokemonService.findPokemonByName(pokemonName);

    // then
    assertThat(result).isEmpty();
  }

  @Test
  void findPokemonByType() {
    // given
    final var pokemonType = "Darkness";

    // when
    var result = pokemonService.findPokemonByType(pokemonType);

    // then
    assertThat(result).isNotEmpty();
    result.forEach(pokemon -> assertThat(pokemon.getTypes()).contains(pokemonType));
  }

  @Test
  void findPokemonByTypeEmpty() {
    // given
    final var pokemonType = "";

    // when
    var result = pokemonService.findPokemonByType(pokemonType);

    // then
    assertThat(result).isEmpty();
  }

  @Test
  void findPokemonByAttackName() {
    // given
    final var attackName = "Feint Attack";

    // when
    var result = pokemonService.findPokemonByAttackName(attackName);

    // then
    assertThat(result).isNotEmpty();
    result.forEach(
        pokemon ->
            assertThat(pokemon.getAttacks())
                .extracting(Pokemon.Attack::getName)
                .contains(attackName));
  }

  @Test
  void findPokemonByAttackNameEmpty() {
    // given
    final var attackName = "";

    // when
    var result = pokemonService.findPokemonByAttackName(attackName);

    // then
    assertThat(result).isEmpty();
  }

  @Test
  void findPokemonByWeakness() {
    // given
    final var weakness = "Fighting";

    // when
    var result = pokemonService.findPokemonByWeakness(weakness);

    // then
    assertThat(result).isNotEmpty();
    result.forEach(
        pokemon ->
            assertThat(pokemon.getWeaknesses())
                .extracting(Pokemon.ValueType::getType)
                .contains(weakness));
  }

  @Test
  void findPokemonByWeaknessEmpty() {
    // given
    final var weakness = "";

    // when
    var result = pokemonService.findPokemonByWeakness(weakness);

    // then
    assertThat(result).isEmpty();
  }

  @Test
  void findPokemonByResistance() {
    // given
    final var resistance = "Psychic";

    // when
    var result = pokemonService.findPokemonByResistance(resistance);

    // then
    assertThat(result).isNotEmpty();
    result.forEach(
        pokemon ->
            assertThat(pokemon.getResistances())
                .extracting(Pokemon.ValueType::getType)
                .contains(resistance));
  }

  @Test
  void findPokemonByResistanceEmpty() {
    // given
    final var resistance = "";

    // when
    var result = pokemonService.findPokemonByResistance(resistance);

    // then
    assertThat(result).isEmpty();
  }

  @Test
  void findPokemonBySetName() {
    // given
    final var setName = "Supreme Victors";

    // when
    var result = pokemonService.findPokemonBySetName(setName);

    // then
    assertThat(result).isNotEmpty();
    result.forEach(
        pokemon ->
            assertThat(pokemon.getSet()).extracting(Pokemon.Set::getName).isEqualTo(setName));
  }

  @Test
  void findPokemonBySetNameEmpty() {
    // given
    final var setName = "";

    // when
    var result = pokemonService.findPokemonBySetName(setName);

    // then
    assertThat(result).isEmpty();
  }

  @Test
  void findPokemonByRarity() {
    // given
    final var pokemonRarity = "Rare Holo";

    // when
    var result = pokemonService.findPokemonByRarity(pokemonRarity);

    // then
    assertThat(result).isNotEmpty();
    result.forEach(pokemon -> assertThat(pokemon.getRarity()).contains(pokemonRarity));
  }

  @Test
  void findPokemonByRarityEmpty() {
    // given
    final var pokemonRarity = "";

    // when
    var result = pokemonService.findPokemonByRarity(pokemonRarity);

    // then
    assertThat(result).isEmpty();
  }
}
