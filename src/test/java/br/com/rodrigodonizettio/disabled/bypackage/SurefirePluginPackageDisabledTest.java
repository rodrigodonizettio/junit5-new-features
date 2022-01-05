package br.com.rodrigodonizettio.disabled.bypackage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

public class SurefirePluginPackageDisabledTest {

  private static Logger log = LogManager.getLogger(SurefirePluginPackageDisabledTest.class);

  @Test
  void anyTest() {
    log.info("Test should be ignored once this package is mapped in pom.xml as excludedGroups at Surefire plugin");
  }
}
