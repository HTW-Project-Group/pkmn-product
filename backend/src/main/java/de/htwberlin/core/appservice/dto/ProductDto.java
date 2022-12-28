package de.htwberlin.core.appservice.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

  UUID id;

  String name;

  double price;

  String description;

  int condition;

  String pokemonId;
}
