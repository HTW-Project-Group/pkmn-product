package de.htwberlin.core.domain.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

import de.htwberlin.core.appservice.mapper.AttributeMapper;
import de.htwberlin.core.domain.model.Product;
import de.htwberlin.core.domain.model.ProductFactory;
import de.htwberlin.core.domain.model.SearchParam;
import de.htwberlin.core.domain.repository.ProductInMemoryRepository;
import de.htwberlin.core.domain.service.ISearchService;
import de.htwberlin.port.adapter.PokemonApiClient;
import de.htwberlin.port.exception.InvalidSearchException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class SearchServiceTest {

  @Nested
  class Search {
    private ISearchService searchService;

    @BeforeEach
    void setUp() {
      var attributeMapper = new AttributeMapper<Product>();
      var productRepository = new ProductInMemoryRepository();

      var products =
          List.of(
              ProductFactory.simpleProduct().name("Pikachu").pokemonId("basep-1").build(),
              ProductFactory.simpleProduct().name("Pikachu").pokemonId("basep-4").build(),
              ProductFactory.simpleProduct().name("Charizard").pokemonId("base1-4").build());
      productRepository.saveAll(products);

      var productService = new ProductService(productRepository, attributeMapper);

      var apiClient = new PokemonApiClient();
      var pokemonService = new PokemonService(apiClient);

      searchService = new SearchService(productService, pokemonService);
    }

    @Test
    void shouldFindProductsByNameParam() {
      // given
      var search = SearchParam.builder().param("name").value("Pikachu").build();

      // when
      var result = searchService.searchForProducts(List.of(search));

      // then
      assertThat(result).hasSize(2);
      assertThat(result)
          .extracting(Product::getPokemonId)
          .containsExactlyInAnyOrder("basep-1", "basep-4");
      assertThat(result.get(0).getName().toLowerCase()).contains("pikachu");
      assertThat(result.get(1).getName().toLowerCase()).contains("pikachu");
    }

    @Test
    void shouldIgnoreCaseOnSearch() {
      // given
      var search = SearchParam.builder().param("name").value("pIKaChU").build();

      // when
      var result = searchService.searchForProducts(List.of(search));

      // then
      assertThat(result).hasSize(2);
      assertThat(result)
          .extracting(Product::getPokemonId)
          .containsExactlyInAnyOrder("basep-1", "basep-4");
      assertThat(result.get(0).getName().toLowerCase()).contains("pikachu");
      assertThat(result.get(1).getName().toLowerCase()).contains("pikachu");
    }

    @Test
    void shouldFindProductWithMultipleSearchParams() {
      // given
      var searchParam1 = SearchParam.builder().param("name").value("charizard").build();
      var searchParam2 = SearchParam.builder().param("types").value("fire").build();

      // when
      var result = searchService.searchForProducts(List.of(searchParam1, searchParam2));

      // then
      assertThat(result).hasSize(1);
      assertThat(result.get(0).getPokemonId()).isEqualTo("base1-4");
      assertThat(result.get(0).getName().toLowerCase()).contains("charizard");
    }

    @Test
    void shouldThrowExceptionWhenParamsAreNotValid() {
      // given
      var search = List.of(SearchParam.builder().param("invalidParam").value("null").build());

      // when + then
      assertThatThrownBy(() -> searchService.searchForProducts(search))
          .isInstanceOf(InvalidSearchException.class);
    }

    @Test
    void shouldThrowExceptionWhenPageSizeIsTooLarge() {
      // given
      var search = List.of(SearchParam.builder().param("name").value("Pikachu").build());
      var pageSize = 9999;

      // when + then
      assertThatThrownBy(() -> searchService.searchForProducts(search, 1, pageSize))
          .isInstanceOf(InvalidSearchException.class);
    }
  }

  @Nested
  class HelperMethods {
    private SearchService searchService;

    @BeforeEach
    void setUp() {
      searchService = new SearchService(mock(ProductService.class), mock(PokemonService.class));
    }

    @Test
    void shouldReturnSublistWithIndexAndSize() {
      // given
      var list = List.of("1", "2", "3", "4", "5", "6", "7");
      var index = 1;
      var size = 3;

      // when
      var result = searchService.getSublistWithIndexAndSize(list, index, size);

      // then
      assertThat(result).hasSize(3).containsExactlyInAnyOrder("1", "2", "3");
    }

    @Test
    void shouldReturnSublistWithIndexAndSizeAlsoWhenLastElementsNotEqualSize() {
      // given
      var list = List.of("1", "2", "3", "4", "5", "6", "7");
      var index = 3;
      var size = 3;

      // when
      var result = searchService.getSublistWithIndexAndSize(list, index, size);

      // then
      assertThat(result).hasSize(1).containsExactlyInAnyOrder("7");
    }

    @Test
    void shouldReturnEmptyListWhenUsingWrongIndex() {
      // given
      var list = List.of("1", "2", "3", "4", "5", "6", "7");
      var index = 1000;
      var size = 3;

      // when
      var result = searchService.getSublistWithIndexAndSize(list, index, size);

      // then
      assertThat(result).isEmpty();
    }

    @Test
    void shouldReturnEmptyListWhenUsingNegativeIndex() {
      // given
      var list = List.of("1", "2", "3", "4", "5", "6", "7");
      var index = -5;
      var size = 3;

      // when
      var result = searchService.getSublistWithIndexAndSize(list, index, size);

      // then
      assertThat(result).isEmpty();
    }
  }
}
