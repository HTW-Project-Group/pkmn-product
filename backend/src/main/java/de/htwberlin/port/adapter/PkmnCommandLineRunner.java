package de.htwberlin.port.adapter;

import de.htwberlin.core.appservice.demodata.IDemodataService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PkmnCommandLineRunner implements CommandLineRunner {

  private final IDemodataService demodataService;

  @Autowired
  public PkmnCommandLineRunner(IDemodataService demodataService) {
    this.demodataService = demodataService;
  }

  @Setter
  @Value("${nr-of-generated-testdata-records}")
  private int numberOfTestData = 0;

  @Override
  public void run(String... args) {
    log.info("Starting PkmnCommandLineRunner");
    if (numberOfTestData == 0) {
      log.info("Number of Test Data Records is set to zero");
      log.info("No Testdata will be created");
      return;
    }
    demodataService.generateTestData(numberOfTestData);
  }
}
