package de.htwberlin.port.dto;

import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductExportDto implements Serializable {

  UUID userId;

  String pokemonId;

  String name;

  String description;

  int quantity;

  double price;
}
