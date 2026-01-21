package io.github.aplaraujo.math;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SimpleMath04Test {
    SimpleMath04 math;

    @BeforeEach
    void setUp() {
        math = new SimpleMath04();
    }

    @DisplayName("Test 10.0 / 2.0 = 5.0")
    @ParameterizedTest
    @MethodSource()
    void testDivide(double firstNumber, double secondNumber, double expected) {
    
        Double actual = math.divide(firstNumber, secondNumber);
        assertEquals(expected, actual, () -> firstNumber + " / " + secondNumber + " did not produce " + expected + "!");
    }

    public static Stream<Arguments> testDivide() {
        return Stream.of(
            Arguments.of(6D, 2D, 3D),
            Arguments.of(10D, 2D, 5D)
        );
    }
}
    