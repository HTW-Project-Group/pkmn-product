package de.htwberlin.core.appservice.demodata;

import de.htwberlin.core.domain.model.ProductFactory;
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
    final int existingProducts = productService.findAllProducts().size();

    if (existingProducts >= count) {
      log.info("Database already contains " + count + " Data Records");
      log.info("No Testdata will be created");
      return;
    }
    if (existingProducts != 0) {
      log.info("Database already contains " + existingProducts + " Data Records");
      log.info("Generating only " + (count - existingProducts));
    }

    for (int c = existingProducts + 1; c <= count; c++) {
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
