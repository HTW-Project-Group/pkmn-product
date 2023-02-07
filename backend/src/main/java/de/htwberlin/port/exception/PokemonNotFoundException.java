package de.htwberlin.port.exception;

public class PokemonNotFoundException extends RuntimeException {

  public PokemonNotFoundException() {
    super("Product not found");
  }

  public PokemonNotFoundException(String id) {
    super("Product not found: " + id);
  }
}
