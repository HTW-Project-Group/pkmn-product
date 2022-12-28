package de.htwberlin.core.domain.service;

import de.htwberlin.core.appservice.dto.ProductDto;
import de.htwberlin.core.domain.model.SearchParam;
import de.htwberlin.port.exception.InvalidSearchException;
import java.util.List;

public interface ISearchService {

  List<ProductDto> searchForProducts(SearchParam... params) throws InvalidSearchException;

  List<ProductDto> searchForProducts(int page, SearchParam... params) throws InvalidSearchException;

  List<ProductDto> searchForProducts(int page, int pageSize, SearchParam... params)
      throws InvalidSearchException;
}
