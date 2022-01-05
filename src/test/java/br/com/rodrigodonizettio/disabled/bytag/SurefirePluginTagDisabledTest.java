package br.com.rodrigodonizettio.disabled.bytag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("integrationDisabled")
public class SurefirePluginTagDisabledTest {

  private static Logger log = LogManager.getLogger(SurefirePluginTagDisabledTest.class);

  @Test
  void anyTest() {
    log.info("Test should be ignored once this class is mapped in pom.xml as excludedGroups at Surefire plugin");
  }
}
