package br.com.rodrigodonizettio;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import br.com.rodrigodonizettio.alphabet.Alphabet;
import br.com.rodrigodonizettio.calc.Calculator;
import br.com.rodrigodonizettio.customsource.annotation.JsonSource;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.mockito.Mock;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestInstance(TestInstance.Lifecycle.PER_METHOD) //Default
//@Execution(ExecutionMode.CONCURRENT)
@Execution(ExecutionMode.SAME_THREAD) //Default
public class JunitJupiterGeneralFeatureTest {
  private static Logger log = LogManager.getLogger(JunitJupiterGeneralFeatureTest.class);

  private List l1 = List.of(1, 2, 3);
  private List l2 = List.of(1, 2, 3);
  private List l3 = List.of(1, 2);

  @Mock
  private Calculator calc = new Calculator();

  @BeforeAll
  static void beforeAllTest() {
    log.info("@BeforeAll - executes once before all test methods in this class");
    //You can configure the Instance Lifecycle through JVM param: -Djunit.jupiter.testinstance.lifecycle.default=per_class

    //You can enable the parallel test execution through JVM param: -Djunit.jupiter.execution.parallel.enabled=true
    //You can configure the parallel test execution to be default through JVM param: -Djunit.jupiter.execution.parallel.mode.default=concurrent
      // or @Execution(ExecutionMode.CONCURRENT) at desired class
    //-Djunit.jupiter.execution.parallel.config.strategy=dynamic | fixed | custom
    //e.g for Dynamic Strategy: -Djunit.jupiter.execution.parallel.config.dynamic.factor=1
    //e.g. for Fixed Strategy: -Djunit.jupiter.execution.parallel.config.fixed.parallelism=4
    //e.g. for Custom Strategy: -Djunit.jupiter.execution.parallel.config.custom.class=br.com.rodrigodonizettio.parallel.MyCustomStrategy
  }

  @BeforeEach
  void beforeEachTest() {
    log.info("@BeforeEach - executes before each test method in this class");
  }

  @AfterEach
  void afterEachTest() {
    log.info("@AfterEach - executed after each test method.");
  }

  @AfterAll
  static void afterAllTest() {
    log.info("@AfterAll - executed after all test methods.");
  }

  @DisplayName("Testing Lifecycle component based on Lifecycle Enum")
  @Test
  void lifeCyclePerMethodOrClass1Test() {
    log.info("Running lifeCyclePerMethodOrClass1 method with object: " + this);
  }

  @Test
  void lifeCyclePerMethodOrClass2Test() {
    log.info("Running lifeCyclePerMethodOrClass2 method with object: " + this);
  }

  @Test
  @Disabled("Not implemented yet!")
  void disabledTest() {
    log.info("@Disabled replaces @Ignore from JUnit4");
  }

  @Test
  @DisabledIf("disabledIfConditionMethod")
  void disabledIfTest() {
    log.info("Test only executes if given condition (can be a variable, an env.var, an -D property, etc) is not set");
  }

  boolean disabledIfConditionMethod() {
    return true;
  }

  @Test
  @DisabledOnOs(OS.WINDOWS)
  void disabledOnOsTest() {
    log.info("Test only executes if OS is not Windows");
  }

  @Test
  @DisabledIfSystemProperty(named = "config.value", matches = "true")
  void disabledIfSystemPropertyTest() {
    log.info("Test only executes if -Dconfig.value values is not set");
  }

  @Test
  @DisabledIfEnvironmentVariable(named = "MY_ENV_VAR", matches = "true")
  void disabledIfEnvironmentVariableTest() {
    log.info("Test only executes if MY_ENV_VAR value is not set");
  }

  @Test
  void lambdaExpressionsTest() {
    log.info("JUnit5 allows the use of Lambdas in Assertions");

    assertTrue(Stream.of(2, 3, 4)
      .mapToInt(i -> i)
      .sum() > 6, () -> "Sum should be greater than 6");
  }

  @Test
  void groupAssertionsTest() {
    log.info("JUnit5 allows the groping of Assertions with assertAll and return any failed error");

    int[] numbers = { 0, 1, 2, 3, 4 };
    assertAll("numbers",
      () -> assertEquals(numbers[0], 0),
      () -> assertEquals(numbers[1], 2, "My Custom Message if Assertion fails!"),
      () -> assertEquals(numbers[2], 2),
      () -> assertNotEquals(numbers[3], 4),
      () -> assertEquals(numbers[4], 4)
    );
  }

  @Test
  void trueAssumptionTest() {
    log.info("Only executes if the Assumption (pre-condition) is true");

    assumeTrue(3 > 2);
    assertEquals(5 + 2, 7);
  }

  @Test
  void falseAssumptionTest() {
    log.info("Only executes if the Assumption (pre-condition) is true");

    assumeFalse(5 < 1);
    assertEquals(5 + 2, 7);
  }

  @Test
  void assumptionThatTest() {
    log.info("Only executes if the Assumption (pre-condition) is true");

    String someString = "Just a string";
    assumingThat(
      someString.equals("Just a string"),
      () -> assertEquals(2 + 2, 4)
    );
  }

  @Test
  void assertThrowsExceptionWhenExactlyNewExceptionIsThrownTest() {
    log.info("Ensures an Exception with given message is thrown");

    Throwable exception = assertThrows(UnsupportedOperationException.class, () -> {
      throw new UnsupportedOperationException("Not supported");
    });
    assertEquals(exception.getMessage(), "Not supported");
  }

  @Test
  void assertThrowsExceptionWhenArgumentIsInvalidTest() {
    log.info("Ensures an Exception occurrence with invalid argument given is thrown");

    String str = null;
    assertThrows(IllegalArgumentException.class, () -> {
      Integer.valueOf(str);
    });
  }

  @Test
  void assertSumOperationDoesNotThrownExceptionTest() {
    assertDoesNotThrow(() -> calc.sum(1, 1));
  }

  @Test
  void assertListValuesAreEqualsTest() {
    assertEquals(l1, l2);
  }

  @Test
  void assertListInMemoryAreNotTheSameTest() {
    assertNotSame(l1, l2);
  }

  @Test
  void assertListValuesAreNotEqualsTest() {
    assertNotEquals(l2, l3);
  }

  @DisplayName("Assert number and order of elements in the collection must be the same, and iterated elements must be equal")
  @Test
  void assertListValuesAreIterableEqualsTest() {
    assertIterableEquals(l1, l2);
  }

  @Test
  void assertIsTrueAndFalseTest() {
    assertTrue(true);
    assertFalse(false);
  }

  @Test
  void assertIsNullAndNotNullTest() {
    String a = null;
    assertNull(a);
    a = "";
    assertNotNull(a);
  }

  @RepeatedTest(value = 3, name = "Iterator Value in Test is: {currentRepetition}")
  void assertTotalRepetitionsTest(RepetitionInfo repetitionInfo) {
    int currentRepetition = repetitionInfo.getCurrentRepetition();
    int totalRepetitions = repetitionInfo.getTotalRepetitions();
    log.info(currentRepetition);

    assertEquals(3, totalRepetitions);
  }

  private static Integer getAlphabetLetterIndex(String letter) {
    switch (letter) {
      case "a":
        return 0;
      case "b":
        return 1;
      case "c":
        return 2;
      default:
        throw new IllegalArgumentException();
    }
  }

  // Requires "junit-jupiter-params" dependency
  // Implicit Conversion
  @ParameterizedTest(name = "Testing the Alphabet letter {0} index")
  @ValueSource(strings = {"a", "b", "c"})
  void assertAlphabetLetterIndexUsingValueSourceTest(String string) {
    Map<String, Integer> abcMap = new HashMap<>(Map.of("a", 0, "b", 1, "c", 2));
    int index = abcMap.get(string);

    assertEquals(getAlphabetLetterIndex(string), index);
  }

  private static List<Arguments> alphabet() {
    return List.of(
            Arguments.of("a", 0),
            Arguments.of("b", 1),
            Arguments.of("c", 2)
    );
  }

  // Implicit Conversion
  @ParameterizedTest
  // @MethodSource("br.com.rodrigodonizettio.JunitJupiterGeneralFeatureTest#alphabet") // Useful for outside method source
  @MethodSource("alphabet")
  void assertAlphabetLetterIndexUsingMethodSourceTest(String letter, Integer index) {
    assertEquals(getAlphabetLetterIndex(letter), index);
  }

  // Implicit Conversion
  @ParameterizedTest
  @EnumSource(Alphabet.class)
  void assertAlphabetLetterIndexUsingEnumSourceTest(Alphabet alphabet) {
    assertEquals(getAlphabetLetterIndex(alphabet.getLetter()), alphabet.getIndex());
  }

  // Implicit Conversion
  @ParameterizedTest
  @CsvSource(value = {
          "1, 2",
          "4, 5"
  }, delimiter = ',')
  void assertInputValueColPlusOneIsEqualExpectedColUsingCsvSourceTest(Integer col1, Integer col2) {
    assertEquals(col2, col1 + 1);
  }

  // Implicit Conversion
  @ParameterizedTest
  @CsvFileSource(resources = "/data.csv")
  void assertInputValueColPlusOneIsEqualExpectedColUsingCsvFileSourceTest(Integer col1, Integer col2) {
    assertEquals(col2, col1 + 1);
  }

  @ParameterizedTest
  @JsonSource(text = "{\"title\": \"The Hard Thing About Hard Things\", \"numberOfPages\": 289}, {\"title\": \"Trillion Dollar Coach\", \"numberOfPages\": 238}")
  void assertJsonArgumentsProviderIsWorkingUsingCustomSourceTest(JsonNode jsonNode) {
    String title = jsonNode.get("title").asText();
    Integer numberOfPages = jsonNode.get("numberOfPages").asInt();

    assertEquals("The Hard Thing About Hard Things", title);
    assertEquals(289, numberOfPages);
  }

  @TestFactory
  Stream<DynamicTest> assertEveryIntegerInStreamIsEvenUsingDynamicTestTest() {
    return IntStream.iterate(2, n -> n + 2).limit(5)
            .mapToObj(n -> DynamicTest.dynamicTest("Assert Number#" + n + " is even",
                    () -> assertTrue(n % 2 == 0)));
  }

}
