package de.htwberlin.product.dto;

import static java.util.Map.entry;

import com.github.javafaker.Faker;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public class ProductFactory {

  private static final Map<String, String> DEMO_DATA =
      Map.<String, String>ofEntries(
          entry("bw5-2", "Ivysaur"),
          entry("sm2-2", "Weepinbell"),
          entry("ex4-2", "Team Aqua's Crawdaunt"),
          entry("base6-2", "Articuno"),
          entry("dp4-2", "Cresselia"),
          entry("xy1-2", "M Venusaur-EX"),
          entry("bw10-3", "Lileep"),
          entry("xy11-3", "Hoppip"),
          entry("xy2-4", "Pineco"),
          entry("bw7-2", "Gloom"),
          entry("col1-3", "Dialga"),
          entry("bw4-2", "Seedot"),
          entry("mcd19-4", "Alolan Sandshrew"),
          entry("gym2-3", "Brock's Ninetales"),
          entry("si1-3", "Onix"),
          entry("ex12-4", "Delcatty"),
          entry("xy7-3", "Vileplume"),
          entry("sm5-2", "Yanma"),
          entry("neo4-2", "Dark Crobat"),
          entry("dv1-4", "Dragonair"),
          entry("sm3-2", "Metapod"),
          entry("det1-4", "Charmander"),
          entry("pop1-2", "Metagross"),
          entry("hgss4-4", "Drapion"),
          entry("bw11-2", "Tangrowth"),
          entry("sm9-4", "Kakuna"),
          entry("bw8-2", "Grotle"),
          entry("xy4-2", "Venomoth"),
          entry("base3-4", "Dragonite"),
          entry("dpp-DP02", "Chimchar"),
          entry("ex9-3", "Exploud"),
          entry("swsh1-2", "Roselia"),
          entry("sm75-1", "Charmander"),
          entry("ex10-1", "Ampharos"),
          entry("bw6-1", "Hoppip"),
          entry("sm12-3", "Gloom"),
          entry("ex3-5", "Golem"),
          entry("pl1-5", "Dialga"),
          entry("pop4-3", "Flygon"),
          entry("bw4-3", "Kricketot"),
          entry("ex8-3", "Breloom"),
          entry("dp3-5", "Flygon"),
          entry("swsh4-2", "Kakuna"),
          entry("xy5-4", "Tangela"),
          entry("ex4-3", "Team Aqua's Kyogre"),
          entry("dv1-5", "Dragonite"),
          entry("dp4-3", "Darkrai"),
          entry("hgss4-5", "Mamoswine"),
          entry("sm75-2", "Charmeleon"),
          entry("swsh35-4", "Beedrill"),
          entry("swsh1-3", "Roselia"),
          entry("gym2-4", "Erika's Venusaur"),
          entry("ex7-4", "Dark Electrode"),
          entry("bw11-3", "Shuckle"),
          entry("dp1-3", "Electivire"),
          entry("bw7-3", "Vileplume"),
          entry("pl4-3", "Heatran"),
          entry("xy4-3", "Yanma"),
          entry("bw10-4", "Cradily"),
          entry("sm5-3", "Yanmega"),
          entry("xy7-4", "Bellossom"),
          entry("mcd19-5", "Lapras"),
          entry("bw6-2", "Skiploom"),
          entry("pop7-4", "Latios"),
          entry("ex9-4", "Gardevoir"),
          entry("ecard2-H5", "Bellossom"),
          entry("basep-2", "Electabuzz"),
          entry("base6-3", "Charizard"),
          entry("ex10-2", "Ariados"),
          entry("xy11-4", "Skiploom"),
          entry("bw1-3", "Servine"),
          entry("neo4-3", "Dark Donphan"),
          entry("hgss2-2", "Magmortar"),
          entry("hgss1-5", "Hitmontop"),
          entry("bw5-3", "Venusaur"),
          entry("ru1-4", "Heatran"));

  private static final Faker faker = new Faker(Locale.GERMAN);

  public static ProductDto.ProductDtoBuilder simpleProduct() {
    final String pokemonId =
        (String) DEMO_DATA.keySet().toArray()[faker.random().nextInt(DEMO_DATA.size())];
    return ProductDto.builder()
        .id(UUID.randomUUID())
        .name(DEMO_DATA.get(pokemonId))
        .price(faker.number().randomDouble(2, 100, 1000))
        .description(faker.lorem().sentence(100))
        .condition(faker.random().nextInt(1, 10))
        .pokemonId(pokemonId);
  }
}
