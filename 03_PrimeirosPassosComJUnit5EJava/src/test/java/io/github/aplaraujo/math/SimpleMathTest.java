package io.github.aplaraujo.math;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class SimpleMathTest {
    @Test
    void testSum() {
        SimpleMath math = new SimpleMath();
        double firstNumber = 6.2D;
        double secondNumber = 2D;
        Double actual = math.sum(firstNumber, secondNumber);
        Double expected = 8.2D;
        assertEquals(expected, actual, () -> firstNumber + " + " + secondNumber + " did not produce " + expected + "!");
    }
}
