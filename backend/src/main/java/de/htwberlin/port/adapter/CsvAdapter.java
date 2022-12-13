package de.htwberlin.port.adapter;

import com.opencsv.bean.CsvToBeanBuilder;
import de.htwberlin.core.domain.model.PokemonIdNamePair;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Component;

@SuppressWarnings("java:S106")
@Component
public class CsvAdapter {

  public Optional<List<PokemonIdNamePair>> readData(String filename) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    PrintStream old = System.out;
    System.setOut(ps);

    try {
      ClassLoader classLoader = getClass().getClassLoader();
      File file = new File(Objects.requireNonNull(classLoader.getResource(filename)).getFile());
      List<PokemonIdNamePair> beans =
          new CsvToBeanBuilder<PokemonIdNamePair>(new FileReader(file))
              .withType(PokemonIdNamePair.class)
              .withSeparator(';')
              .build()
              .parse();
      System.setOut(old);
      return Optional.ofNullable(beans);
    } catch (Exception e) {
      System.setOut(old);
      return Optional.empty();
    }
  }
}
