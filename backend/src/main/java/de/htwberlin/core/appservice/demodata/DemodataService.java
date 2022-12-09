package de.htwberlin.core.appservice.demodata;

import de.htwberlin.core.appservice.dto.ProductFactory;
import de.htwberlin.core.domain.service.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class DemodataService implements IDemodataService {

  private final IProductService productService;

  @Override
  public void generateTestData(int nrOfTestData) {
    log.info("Generating " + nrOfTestData + " Data Samples...");
    generateProducts(nrOfTestData);
  }

  private void generateProducts(int count) {
    for (int c = 1; c <= count; c++) {
      var product = ProductFactory.simpleProduct().build();
      if (productService.findProductByPokemonId(product.getPokemonId()).isEmpty()) {
        productService.createProduct(product);
        log.info(
            genIdx(c, count)
                + "Inserted "
                + product.getName()
                + " ("
                + product.getPokemonId()
                + ") into ProductRepository.");
      } else {
        log.info(
            genIdx(c, count)
                + "Product "
                + product.getName()
                + " already exists. Nothing changed.");
      }
    }
  }

  private String genIdx(int c, int n) {
    return "[" + c + "/" + n + "] ";
  }
}
