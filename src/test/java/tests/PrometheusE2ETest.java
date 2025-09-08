package tests;

import framework.core.Driver;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrometheusE2ETest {

  @BeforeAll
  static void setup() { Driver.init(); }

  @AfterAll
  static void teardown() { Driver.quit(); }

  @Test
  public void sample() {
    Driver.page.navigate(Driver.cfg.getProperty("baseUrlGoogle"));
    assertTrue(Driver.page.title().toLowerCase().contains("google"));
  }
}
