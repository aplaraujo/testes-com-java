package io.github.aplaraujo.math;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
@SuppressWarnings("unused")
public class SimpleMathTest {
    // @BeforeAll - método executado antes de todos os testes unitários
    // @BeforeEach - método executado antes de cada teste unitário
    // @AfterEach - método executado depois de cada teste unitário
    // @AfterAll - método executado depois de todos os testes unitários

    @BeforeAll
    static void setUp() {
        System.out.println("Method @BeforeAll running!");
    }

    @AfterAll // limpeza dos testes
    static void cleanUp() {
        System.out.println("Method @AfterAll running!");
    }

    SimpleMath math;

    @BeforeEach
    void beforeEachSetUp() {
        math = new SimpleMath();
        System.out.println("Method @BeforeEach running!");
    }

    @AfterEach
    void afterEachCleanUp() {
        System.out.println("Method @AfterEach running!");
    }

    @Test
    @DisplayName("Test 6.2 + 2 = 8.2")
    void testSum() {
        // Given (Arrange) - definição das variáveis que serão usadas no teste
        
        double firstNumber = 6.2D;
        double secondNumber = 2D;
        Double expected = 8.2D;
        // When (Act) - invocação das variáveis que serão testadas
        Double actual = math.sum(firstNumber, secondNumber);
        // Then (Assert) - verifica o comportamento esperado do teste (validação dos métodos invocados)
        assertEquals(expected, actual, () -> firstNumber + " + " + secondNumber + " did not produce " + expected + "!");
    }

    @Test
    @DisplayName("Test 6.2 - 2 = 4.2")
    void testSubtract() {
        
        double firstNumber = 6.2D;
        double secondNumber = 2D;
        Double actual = math.subtract(firstNumber, secondNumber);
        Double expected = 4.2D;
        assertEquals(expected, actual, () -> firstNumber + " - " + secondNumber + " did not produce " + expected + "!");
    }

    @Test
    @DisplayName("Test 5.0 * 2.0 = 10.0")
    void testMultiply() {
        
        double firstNumber = 5D;
        double secondNumber = 2D;
        Double actual = math.multiply(firstNumber, secondNumber);
        Double expected = 10.0D;
        assertEquals(expected, actual, () -> firstNumber + " * " + secondNumber + " did not produce " + expected + "!");
    }

    @Test
    @DisplayName("Test 10.0 / 2.0 = 5.0")
    void testDivide() {
        
        double firstNumber = 10.0D;
        double secondNumber = 2D;
        Double actual = math.divide(firstNumber, secondNumber);
        Double expected = 5D;
        assertEquals(expected, actual, () -> firstNumber + " / " + secondNumber + " did not produce " + expected + "!");
    }

    @Test
    @DisplayName("Test (10.0 + 2.0) / 2.0 = 6.0")
    void testMean() {
        
        double firstNumber = 10.0D;
        double secondNumber = 2D;
        Double actual = math.mean(firstNumber, secondNumber);
        Double expected = 6D;
        assertEquals(expected, actual, () -> firstNumber + " / " + secondNumber + " did not produce " + expected + "!");
    }

    @Test
    @DisplayName("Test square root of 4.0 = 2.0")
    void testSquareRoot() {
        
        double number = 4D;
        Double actual = math.squareRoot(number);
        Double expected = 2D;
        assertEquals(expected, actual, () -> "The square root of " + number + " did not produce  " + expected + "!");
    }

    @Test
    @DisplayName("Test division by zero")
    void testDivision_WhenFirstNumberIsDividedByZero_ShouldThrowArithmeticException() {
        double firstNumber = 6D;
        double secondNumber = 0D;

        var message = "Impossible to divide by zero!";

        ArithmeticException actual = assertThrows(ArithmeticException.class, () -> {
            math.divide(firstNumber, secondNumber);
        }, () -> "Division by zero should throw an ArithmeticException ");

        assertEquals(message, actual.getMessage(), () -> "Unexpected exception message!");
    }

    // Template
    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    // Exemplo: testSum_WhenSixDotTwoIsAddedByTwo_ShouldReturnEightDotTwo
    @Disabled // Anotação usada para desabilitar testes
    @Test
    @DisplayName("Display name")
    void testABCD_When_XYZ_Should() {
        // Given / Arrange
        // When / Act
        // Then / Assert
    }
}
