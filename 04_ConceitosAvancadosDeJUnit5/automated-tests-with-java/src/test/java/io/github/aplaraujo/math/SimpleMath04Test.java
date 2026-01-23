package io.github.aplaraujo.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class SimpleMath04Test {
    SimpleMath04 math;

    @BeforeEach
    void setUp() {
        math = new SimpleMath04();
    }

    // @DisplayName("Test 10.0 / 2.0 = 5.0")
    // @ParameterizedTest
    // @MethodSource()
    // void testDivide(double firstNumber, double secondNumber, double expected) {
    
    //     Double actual = math.divide(firstNumber, secondNumber);
    //     assertEquals(expected, actual, () -> firstNumber + " / " + secondNumber + " did not produce " + expected + "!");
    // }

    // public static Stream<Arguments> testDivide() {
    //     return Stream.of(
    //         Arguments.of(6D, 2D, 3D),
    //         Arguments.of(10D, 2D, 5D)
    //     );
    // }

    @ParameterizedTest
    // @CsvSource({
    //     "6.0, 2.0, 3.0",
    //     "10.0, 2.0, 5.0"
    // })
    @CsvFileSource(resources="/test.csv")
    void testDivide(double firstNumber, double secondNumber, double expected) {
    
        Double actual = math.divide(firstNumber, secondNumber);
        assertEquals(expected, actual, () -> firstNumber + " / " + secondNumber + " did not produce " + expected + "!");
    }
}
    