package de.htwberlin.core.domain.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PokemonData implements Serializable {

  private Pokemon data;
}
