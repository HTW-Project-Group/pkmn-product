package de.htwberlin.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchParam {

  private String param;
  private String value;

  public enum Params {
    ID("id"),
    NAME("name"),
    SUPERTYPE("supertype"),
    SUBTYPES("subtypes"),
    LEVEL("level"),
    HP("hp"),
    TYPES("types"),
    EVOLVES_FROM("evolvesFrom"),
    EVOLVES_TO("evolvesTo"),
    ABILITIES("abilities"),
    ATTACKS("attacks"),
    WEAKNESSES("weaknesses"),
    RESISTANCES("resistances"),
    RETREAT_COST("retreatCost"),
    SET("set"),
    NUMBER("number"),
    ARTIST("artist"),
    RARITY("rarity");

    private final String value;

    Params(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    public static boolean contains(String value) {
      for (Params p : Params.values()) {
        if (p.getValue().equals(value)) {
          return true;
        }
      }
      return false;
    }
  }
}
