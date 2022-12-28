package de.htwberlin.core.domain.service.impl;

import de.htwberlin.core.appservice.dto.ProductDto;
import de.htwberlin.core.domain.model.SearchParam;
import de.htwberlin.core.domain.service.IPokemonService;
import de.htwberlin.core.domain.service.IProductService;
import de.htwberlin.core.domain.service.ISearchService;
import de.htwberlin.port.exception.InvalidSearchException;
import java.util.List;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService implements ISearchService {

  private final IProductService productService;
  private final IPokemonService pokemonService;

  @Override
  public List<ProductDto> searchForProducts(SearchParam... params) throws InvalidSearchException {
    return searchForProducts(1, 250, params);
  }

  @Override
  public List<ProductDto> searchForProducts(int page, SearchParam... params)
      throws InvalidSearchException {
    return searchForProducts(page, 250, params);
  }

  @Override
  public List<ProductDto> searchForProducts(int page, int pageSize, SearchParam... params)
      throws InvalidSearchException {
    if (pageSize > 250) {
      throw new InvalidSearchException("The max Page Size is 250");
    }
    Stream.of(params)
        .forEach(
            p -> {
              if (!SearchParam.Params.contains(p.getParam())) {
                throw new InvalidSearchException(params);
              }
            });

    var mappedParams =
        Stream.of(params)
            .map(p -> p.getParam() + ":\"*" + p.getValue().toLowerCase() + "*\"")
            .toList();
    var resultIds = pokemonService.findPokemonIdsBySearchQuery(String.join(" ", mappedParams));

    return productService.findAllProducts().stream()
        .filter(p -> resultIds.contains(p.getPokemonId()))
        .toList();
  }
}
