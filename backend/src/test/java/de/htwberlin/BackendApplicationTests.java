package de.htwberlin;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class BackendApplicationTests {

  @Test
  void contextLoads(ApplicationContext context) {
    assertThat(context).isNotNull();
  }
}
