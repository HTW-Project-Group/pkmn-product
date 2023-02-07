package de.htwberlin.port.product;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import de.htwberlin.core.domain.service.*;
import de.htwberlin.port.adapter.PokemonApiClient;
import de.htwberlin.port.annotation.IntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@IntegrationTest
public class PokemonControllerIntegrationTest {
  private MockMvc mvc;
  @MockBean private IPokemonService pokemonService;
  @MockBean private PokemonController pokemonController;
  @MockBean private PokemonApiClient pokemonApiClient;

  @BeforeEach
  void setUp() {
    // given
    pokemonApiClient = new PokemonApiClient();
    pokemonService = new PokemonService(pokemonApiClient);
    pokemonController = new PokemonController(pokemonService);
    mvc = MockMvcBuilders.standaloneSetup(pokemonController).build();
  }

  @Test
  void shouldReturnPokemonWithGivenIdThenStatus200() throws Exception {
    // given + when
    mvc.perform(get("/v1/pokemon/swsh9-166"))

        // then
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.id").value("swsh9-166"));
  }
}
