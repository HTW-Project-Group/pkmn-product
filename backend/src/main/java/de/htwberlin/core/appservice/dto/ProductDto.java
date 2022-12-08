package de.htwberlin.core.appservice.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {

  UUID id;

  String name;

  double price;

  String description;

  int condition;

  String pokemonId;
}
