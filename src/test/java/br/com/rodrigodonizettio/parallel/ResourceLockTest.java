package br.com.rodrigodonizettio.parallel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.api.parallel.ResourceAccessMode;
import org.junit.jupiter.api.parallel.ResourceLock;

import java.util.HashMap;
import java.util.Map;

@Execution(ExecutionMode.CONCURRENT)
public class ResourceLockTest {
  //TODO: Try to change the lock scope or remove the @ResourceLock from methods (the tests will break due to parallel execution for shared attributes)
  private static Logger log = LogManager.getLogger(ResourceLockTest.class);
  private static Map<Integer, String> GLOBAL_USERS = new HashMap<>();

  @BeforeEach
  void beforeEach() {
    log.info("@BeforeEach");
    GLOBAL_USERS.clear();
  }

  @Test
  @ResourceLock(value="scopeLockA", mode=ResourceAccessMode.READ)
  void isEmpty_Test() {
    System.out.println("isEmpty_Test() : " + GLOBAL_USERS.values());
    Assertions.assertTrue(GLOBAL_USERS.values().isEmpty());
  }

  @Test
  @ResourceLock(value="scopeLockA", mode=ResourceAccessMode.READ_WRITE)
  void add_Test() {
    GLOBAL_USERS.put(1, "peter");
    System.out.println("add_Test() : " + GLOBAL_USERS.values());
    Assertions.assertEquals("peter", GLOBAL_USERS.get(1));
  }

  @Test
  @ResourceLock(value="scopeLockA", mode=ResourceAccessMode.READ_WRITE)
  void update_Test() {
    GLOBAL_USERS.put(1, "john");
    System.out.println("update_Test() : " + GLOBAL_USERS.values());
    Assertions.assertEquals("john", GLOBAL_USERS.get(1));
  }

  @Test
  @ResourceLock(value="scopeLockA", mode=ResourceAccessMode.READ_WRITE)
  void remove_Test() {
    GLOBAL_USERS.put(2, "Anand");
    GLOBAL_USERS.remove(2);
    System.out.println("remove_Test() : " + GLOBAL_USERS.values());
    Assertions.assertNull(GLOBAL_USERS.get(2));
  }
}
