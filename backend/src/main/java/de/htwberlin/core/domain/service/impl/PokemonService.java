package de.htwberlin.core.domain.service.impl;

import de.htwberlin.core.domain.model.Pokemon;
import de.htwberlin.core.domain.model.PokemonData;
import de.htwberlin.core.domain.model.PokemonDataList;
import de.htwberlin.core.domain.service.IPokemonService;
import de.htwberlin.port.adapter.PokemonApiClient;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PokemonService extends ApiService implements IPokemonService {

  private static final String POKEMON_CARDS = "/cards/";

  public PokemonService(PokemonApiClient apiClient) {
    super(apiClient);
  }

  @Override
  public Optional<Pokemon> findPokemonById(String id) {
    return getRestObjectFromUri(POKEMON_CARDS + id, PokemonData.class).map(PokemonData::getData);
  }

  @Override
  public List<Pokemon> findPokemonByName(String name) {
    if (name.isEmpty()) return List.of();

    final var q = toQueryParam(name);
    return getRestObjectFromUri(POKEMON_CARDS + "?pageSize=50&q=name:" + q, PokemonDataList.class)
        .map(PokemonDataList::getData)
        .orElse(List.of());
  }

  @Override
  public List<Pokemon> findPokemonByType(String type) {
    if (type.isEmpty()) return List.of();

    final var q = toQueryParam(type);
    return getRestObjectFromUri(POKEMON_CARDS + "?pageSize=50&q=types:" + q, PokemonDataList.class)
        .map(PokemonDataList::getData)
        .orElse(List.of());
  }

  @Override
  public List<Pokemon> findPokemonByAttackName(String attackName) {
    if (attackName.isEmpty()) return List.of();

    final var q = toQueryParam(attackName);
    return getRestObjectFromUri(
            POKEMON_CARDS + "?pageSize=50&q=attacks.name:" + q, PokemonDataList.class)
        .map(PokemonDataList::getData)
        .orElse(List.of());
  }

  @Override
  public List<Pokemon> findPokemonByWeakness(String weakness) {
    if (weakness.isEmpty()) return List.of();

    final var q = toQueryParam(weakness);
    return getRestObjectFromUri(
            POKEMON_CARDS + "?pageSize=50&q=weaknesses.type:" + q, PokemonDataList.class)
        .map(PokemonDataList::getData)
        .orElse(List.of());
  }

  @Override
  public List<Pokemon> findPokemonByResistance(String resistance) {
    if (resistance.isEmpty()) return List.of();

    final var q = toQueryParam(resistance);
    return getRestObjectFromUri(
            POKEMON_CARDS + "?pageSize=50&q=resistances.type:" + q, PokemonDataList.class)
        .map(PokemonDataList::getData)
        .orElse(List.of());
  }

  @Override
  public List<Pokemon> findPokemonBySetName(String setName) {
    if (setName.isEmpty()) return List.of();

    final var q = toQueryParam(setName);
    return getRestObjectFromUri(
            POKEMON_CARDS + "?pageSize=50&q=set.name:" + q, PokemonDataList.class)
        .map(PokemonDataList::getData)
        .orElse(List.of());
  }

  @Override
  public List<Pokemon> findPokemonByRarity(String rarity) {
    if (rarity.isEmpty()) return List.of();

    final var q = toQueryParam(rarity);
    return getRestObjectFromUri(POKEMON_CARDS + "?pageSize=50&q=rarity:" + q, PokemonDataList.class)
        .map(PokemonDataList::getData)
        .orElse(List.of());
  }

  @Override
  public List<String> findPokemonIdsBySearchQuery(String searchQuery) {
    return getRestObjectFromUri(
            POKEMON_CARDS + "?pageSize=250&select=id&q=" + searchQuery, PokemonDataList.class)
        .map(PokemonDataList::getData)
        .orElse(List.of())
        .stream()
        .map(Pokemon::getId)
        .toList();
  }

  private String toQueryParam(String str) {
    return str.contains(" ") ? ("\"" + str + "\"") : str;
  }
}
