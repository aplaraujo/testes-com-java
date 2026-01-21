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

    @Test
    void testSubtract() {
        SimpleMath math = new SimpleMath();
        double firstNumber = 6.2D;
        double secondNumber = 2D;
        Double actual = math.subtract(firstNumber, secondNumber);
        Double expected = 4.2D;
        assertEquals(expected, actual, () -> firstNumber + " - " + secondNumber + " did not produce " + expected + "!");
    }

    @Test
    void testMultiply() {
        SimpleMath math = new SimpleMath();
        double firstNumber = 5D;
        double secondNumber = 2D;
        Double actual = math.multiply(firstNumber, secondNumber);
        Double expected = 10.0D;
        assertEquals(expected, actual, () -> firstNumber + " * " + secondNumber + " did not produce " + expected + "!");
    }

    @Test
    void testDivide() {
        SimpleMath math = new SimpleMath();
        double firstNumber = 10.0D;
        double secondNumber = 2D;
        Double actual = math.divide(firstNumber, secondNumber);
        Double expected = 5D;
        assertEquals(expected, actual, () -> firstNumber + " / " + secondNumber + " did not produce " + expected + "!");
    }

    @Test
    void testMean() {
        SimpleMath math = new SimpleMath();
        double firstNumber = 10.0D;
        double secondNumber = 2D;
        Double actual = math.mean(firstNumber, secondNumber);
        Double expected = 6D;
        assertEquals(expected, actual, () -> firstNumber + " / " + secondNumber + " did not produce " + expected + "!");
    }

    @Test
    void testSquareRoot() {
        SimpleMath math = new SimpleMath();
        double number = 4D;
        Double actual = math.squareRoot(number);
        Double expected = 2D;
        assertEquals(expected, actual, () -> "The square root of " + number + " did not produce  " + expected + "!");
    }
}
