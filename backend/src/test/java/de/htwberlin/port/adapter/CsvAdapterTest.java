package de.htwberlin.port.adapter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CsvAdapterTest {

  private CsvAdapter csvAdapter;

  @BeforeEach
  void setUp() {
    csvAdapter = new CsvAdapter();
  }

  @Test
  void adapterShouldReadFile() {
    // given
    final var CSV_PATH = "test.csv";

    // when
    final var readData = csvAdapter.readData(CSV_PATH).orElseThrow(NullPointerException::new);

    // then
    Assertions.assertThat(readData).hasSize(10);
  }
}
