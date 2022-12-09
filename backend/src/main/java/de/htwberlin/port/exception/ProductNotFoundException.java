package de.htwberlin.port.exception;

public class ProductNotFoundException extends RuntimeException {
  public ProductNotFoundException() {
    super("Product not found");
  }

  public ProductNotFoundException(String id) {
    super("Product not found: " + id);
  }
}
