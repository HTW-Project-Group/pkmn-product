package de.htwberlin.core.domain.service;

import de.htwberlin.core.domain.model.Product;
import de.htwberlin.core.domain.model.SearchParam;
import de.htwberlin.port.exception.InvalidSearchException;
import java.util.List;

public interface ISearchService {

  List<Product> searchForProducts(List<SearchParam> params) throws InvalidSearchException;

  List<Product> searchForProducts(List<SearchParam> params, int page) throws InvalidSearchException;

  List<Product> searchForProducts(List<SearchParam> params, int page, int pageSize)
      throws InvalidSearchException;
}
