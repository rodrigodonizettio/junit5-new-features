package br.com.rodrigodonizettio.calc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import static org.junit.jupiter.api.Assertions.*;

public class CalculatorUnitTest {
    private static Logger log = LogManager.getLogger(CalculatorUnitTest.class);

    @Mock
    Calculator calculator = new Calculator();


    @Test
    void sumShouldReturnTwoTest() {
        Assertions.assertEquals(2, calculator.sum(1, 1), "Sum will result 2");
    }

    @Test
    void subShouldReturnZeroTest() {
        Assertions.assertEquals(0, calculator.sub(1, 1), "Sub will result 0");
    }

    @Test
    void mulShouldReturnOneTest() {
        Assertions.assertEquals(1, calculator.mul(1, 1), "Mul will result 1");
    }

    @Test
    void divShouldReturnOneTest() {
        Assertions.assertEquals(1, calculator.div(1, 1), "Div will result 1");
    }

    @Test
    void divShouldThrowArithmeticExceptionTest() {
        Throwable exception = assertThrows(ArithmeticException.class, () -> {
            calculator.div(1, 0);
        });
        assertEquals(exception.getClass(), ArithmeticException.class);
        assertEquals(exception.getMessage(), "/ by zero");
    }

    @Test
    void sumStubShouldReturn2Test() {
        assertEquals(2, calculator.sumWillReturn2(), "Sum will result 2");
    }
}
