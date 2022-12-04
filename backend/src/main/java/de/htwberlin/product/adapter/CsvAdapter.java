package de.htwberlin.product.adapter;

import com.opencsv.bean.CsvToBeanBuilder;
import de.htwberlin.product.model.PokemonIdNamePair;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class CsvAdapter {

  public Optional<List<PokemonIdNamePair>> readData(String filename) {
    try {
      ClassLoader classLoader = getClass().getClassLoader();
      File file = new File(Objects.requireNonNull(classLoader.getResource(filename)).getFile());
      List<PokemonIdNamePair> beans =
          new CsvToBeanBuilder<PokemonIdNamePair>(new FileReader(file))
              .withType(PokemonIdNamePair.class)
              .withSeparator(';')
              .build()
              .parse();
      return Optional.ofNullable(beans);
    } catch (Exception e) {
      return Optional.empty();
    }
  }
}
