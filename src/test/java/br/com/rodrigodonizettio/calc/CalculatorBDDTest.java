package br.com.rodrigodonizettio.calc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("GIVEN a Calculator")
public class CalculatorBDDTest {
    private static Logger log = LogManager.getLogger(CalculatorBDDTest.class);

    @Mock
    Calculator calculator = new Calculator();

    private int accumulator;

    @BeforeEach
    void beforeEachParentTest() {
        log.info("BeforeEach - Parent");

        accumulator = 0;
    }

    @AfterEach
    void afterEachParentTest() {
        log.info("AfterEach - Parent");

        accumulator = 0;
    }

    @Nested
    @DisplayName("WHEN an isolated operation is performed")
    class CalculatorBDDChildTest {

        @BeforeEach
        void beforeEachChildTest() {
            log.info("BeforeEach - Child");
        }

        @AfterEach
        void afterEachChildTest() {
            log.info("AfterEach - Child");
        }

        @Test
        @DisplayName("THEN accumulator after 1+1 sum must value 2")
        void childMethod1Test() {
            log.info("Child - Method#1 - Test");

            accumulator += calculator.sum(1, 1);
            assertEquals(2, accumulator);
        }

        @Test
        @DisplayName("THEN accumulator after 1-1 sub must value 0")
        void childMethod2Test() {
            log.info("Child - Method#2 - Test");

            accumulator += calculator.sub(1, 1);
            assertEquals(0, accumulator);
        }
    }
}
