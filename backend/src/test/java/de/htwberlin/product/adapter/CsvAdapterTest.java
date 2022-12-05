package de.htwberlin.product.adapter;

import static org.assertj.core.api.Assertions.assertThat;

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
    assertThat(readData).hasSize(10);
  }
}
