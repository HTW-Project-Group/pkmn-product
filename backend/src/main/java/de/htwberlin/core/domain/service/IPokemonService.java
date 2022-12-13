package de.htwberlin.core.domain.service;

import de.htwberlin.core.domain.model.Pokemon;
import java.util.List;
import java.util.Optional;

public interface IPokemonService {

  Optional<Pokemon> findPokemonById(String id);

  List<Pokemon> findPokemonByName(String name);

  List<Pokemon> findPokemonByType(String type);

  List<Pokemon> findPokemonByAttackName(String attackName);

  List<Pokemon> findPokemonByWeakness(String weakness);

  List<Pokemon> findPokemonByResistance(String resistance);

  List<Pokemon> findPokemonBySetName(String setName);

  List<Pokemon> findPokemonByRarity(String rarity);
}
