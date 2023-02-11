package de.htwberlin.core.domain.service.impl;

import de.htwberlin.core.appservice.dto.ProductDto;
import de.htwberlin.core.domain.model.SearchParam;
import de.htwberlin.core.domain.service.IPokemonService;
import de.htwberlin.core.domain.service.IProductService;
import de.htwberlin.core.domain.service.ISearchService;
import de.htwberlin.port.exception.InvalidSearchException;
import java.util.ArrayList;
import java.util.List;
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
  public List<ProductDto> searchForProducts(List<SearchParam> params)
      throws InvalidSearchException {
    return searchForProducts(params, 1, 250);
  }

  @Override
  public List<ProductDto> searchForProducts(List<SearchParam> params, int page)
      throws InvalidSearchException {
    return searchForProducts(params, page, 250);
  }

  @Override
  public List<ProductDto> searchForProducts(List<SearchParam> params, int page, int pageSize)
      throws InvalidSearchException {
    if (pageSize > 250) {
      throw new InvalidSearchException("The max Page Size is 250");
    }
    params.forEach(
        p -> {
          if (!SearchParam.Params.contains(p.getParam())) {
            throw new InvalidSearchException(params);
          }
        });

    if (params.size() == 1 && params.get(0).getParam().equals("name")) {
      var name = params.get(0).getValue();
      var resultList =
          productService.findAllProducts().stream()
              .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
              .toList();
      return getSublistWithIndexAndSize(resultList, page, pageSize);
    }

    var mappedParams =
        params.stream()
            .map(p -> p.getParam() + ":\"*" + p.getValue().toLowerCase() + "*\"")
            .toList();
    var resultIds = pokemonService.findPokemonIdsBySearchQuery(String.join(" ", mappedParams));

    var resultList =
        productService.findAllProducts().stream()
            .filter(p -> resultIds.contains(p.getPokemonId()))
            .toList();

    return getSublistWithIndexAndSize(resultList, page, pageSize);
  }

  protected <T> List<T> getSublistWithIndexAndSize(List<T> list, int index, int size) {
    List<List<T>> resultList = new ArrayList<>();
    List<T> tmp = new ArrayList<>();

    for (T p : list) {
      tmp.add(p);
      if (tmp.size() == size) {
        resultList.add(tmp);
        tmp = new ArrayList<>();
      }
    }
    resultList.add(tmp);

    var i = index - 1;
    return i < resultList.size() && i >= 0 ? resultList.get(i) : List.of();
  }
}
