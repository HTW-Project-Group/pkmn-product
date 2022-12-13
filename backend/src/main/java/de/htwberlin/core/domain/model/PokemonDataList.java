package de.htwberlin.core.domain.model;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PokemonDataList implements Serializable {

  private List<Pokemon> data;
}
