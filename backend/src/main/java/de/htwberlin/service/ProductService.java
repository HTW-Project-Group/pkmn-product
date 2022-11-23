package de.htwberlin.service;

import de.htwberlin.adapter.CsvAdapter;
import de.htwberlin.entity.ProductEntity;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

  private static final String PRODUCT_CSV_PATH = "resources/Produkte.csv";
  private CsvAdapter csvAdapter;

  public List<ProductEntity> findAllProducts() throws IOException {
    return csvAdapter
        .readData(PRODUCT_CSV_PATH)
        .orElseThrow(() -> new IOException("Failed to load CSV"));
  }

  public Optional<ProductEntity> findProductById(UUID id) throws IOException {
    return Optional.ofNullable(
        csvAdapter
            .readData(PRODUCT_CSV_PATH)
            .orElseThrow(() -> new IOException("Failed to load CSV"))
            .stream()
            .collect(Collectors.toMap(ProductEntity::getId, entity -> entity))
            .get(id));
  }
}
