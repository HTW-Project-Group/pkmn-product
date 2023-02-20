package de.htwberlin.port.product.controller;

import de.htwberlin.core.appservice.dto.ProductDto;
import de.htwberlin.core.domain.model.SearchParam;
import de.htwberlin.core.domain.service.ISearchService;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/search")
public class SearchController {

  private final ISearchService searchService;

  @GetMapping("/{query}")
  public ResponseEntity<List<ProductDto>> searchProducts(@PathVariable("query") String query) {
    return new ResponseEntity<>(
        searchService.searchForProducts(getSearchParamsFromQuery(query)), HttpStatus.OK);
  }

  @GetMapping("/{query}/{page}")
  public ResponseEntity<List<ProductDto>> searchProducts(
      @PathVariable("query") String query, @PathVariable("page") int page) {

    return new ResponseEntity<>(
        searchService.searchForProducts(getSearchParamsFromQuery(query), page), HttpStatus.OK);
  }

  @GetMapping("/{query}/{page}/{pageSize}")
  public ResponseEntity<List<ProductDto>> searchProducts(
      @PathVariable("query") String query,
      @PathVariable("page") int page,
      @PathVariable("pageSize") int pageSize) {

    return new ResponseEntity<>(
        searchService.searchForProducts(getSearchParamsFromQuery(query), page, pageSize),
        HttpStatus.OK);
  }

  private List<SearchParam> getSearchParamsFromQuery(String query) {
    return Arrays.stream(query.split(";"))
        .map(
            q -> {
              var param = q.split(":");
              if (param.length < 2) return null;
              return new SearchParam(param[0], param[1]);
            })
        .filter(Objects::nonNull)
        .toList();
  }
}
