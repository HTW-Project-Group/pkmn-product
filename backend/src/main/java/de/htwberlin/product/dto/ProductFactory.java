package de.htwberlin.product.dto;

import com.github.javafaker.Faker;
import de.htwberlin.product.adapter.CsvAdapter;
import de.htwberlin.product.model.PokemonIdNamePair;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public class ProductFactory {

  private static final Faker faker = new Faker(Locale.GERMAN);
  private static List<PokemonIdNamePair> pokemons = null;

  public static ProductDto.ProductDtoBuilder simpleProduct() {
    var builder =
        ProductDto.builder()
            .id(UUID.randomUUID())
            .price(faker.number().randomDouble(2, 100, 1000))
            .description(faker.lorem().sentence(100))
            .condition(faker.random().nextInt(1, 10));

    if (pokemons == null) {
      var csvAdapter = new CsvAdapter();
      csvAdapter.readData("pokemon_ids.csv").ifPresent(data -> pokemons = data);
    }

    if (pokemons != null) {
      final PokemonIdNamePair pokemonIdNamePair =
          pokemons.get(faker.random().nextInt(pokemons.size()));
      builder.name(pokemonIdNamePair.getName());
      builder.pokemonId(pokemonIdNamePair.getId());
    } else {
      builder.name(faker.pokemon().name());
      builder.pokemonId("P" + faker.pokemon().hashCode());
    }

    return builder;
  }
}
