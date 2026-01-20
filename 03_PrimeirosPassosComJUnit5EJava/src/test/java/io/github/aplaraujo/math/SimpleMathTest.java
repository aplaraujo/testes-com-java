package io.github.aplaraujo.math;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleMathTest {
    @Test
    void testSum() {
        SimpleMath math = new SimpleMath();
        Double actual = math.sum(6.2D, 2D);
        Double expected = 8.2D;
        Assertions.assertEquals(expected, actual);
    }
}
