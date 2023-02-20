package de.htwberlin.port.product.controller;

import de.htwberlin.core.domain.model.Pokemon;
import de.htwberlin.core.domain.service.IPokemonService;
import de.htwberlin.port.exception.PokemonNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/product/pokemon")
public class PokemonController {

  private final IPokemonService pokemonService;

  @GetMapping("/{id}")
  public ResponseEntity<Pokemon> getPokemonById(@PathVariable("id") String id)
      throws PokemonNotFoundException {
    return new ResponseEntity<>(
        pokemonService.findPokemonById(id).orElseThrow(PokemonNotFoundException::new),
        HttpStatus.OK);
  }
}
