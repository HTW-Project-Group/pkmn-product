package de.htwberlin.adapter;

import de.htwberlin.entity.ProductEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class CsvAdapter {

  public Optional<List<ProductEntity>> readData(String filename) {
    try {
      // will be removed after migration
      List<ProductEntity> beans = List.of();
      return Optional.ofNullable(beans);
    } catch (Exception e) {
      return Optional.empty();
    }
  }
}
