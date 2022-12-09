package de.htwberlin.core.domain.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PokemonIdNamePair {
  @CsvBindByPosition(position = 0)
  String id;

  @CsvBindByPosition(position = 1)
  String name;
}
