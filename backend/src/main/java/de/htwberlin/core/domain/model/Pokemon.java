package de.htwberlin.core.domain.model;

import java.io.Serializable;
import java.util.List;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon implements Serializable {

  private String id;
  private String name;
  private String supertype;
  private List<String> subtypes;
  private String level;
  private String hp;
  private List<String> types;
  private List<Attack> attacks;
  private List<ValueType> weaknesses;
  private List<ValueType> resistances;
  private Set set;
  private String number;
  private String artist;
  private String rarity;

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Attack implements Serializable {
    private String name;
    private List<String> cost;
    private Integer convertedEnergyCost;
    private String damage;
    private String text;
  }

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ValueType implements Serializable {
    private String type;
    private String value;
  }

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Set implements Serializable {
    private String id;
    private String name;
    private String series;
    private Integer printedTotal;
    private Integer total;
    private String ptcgoCode;
    private String releaseDate;
  }
}
