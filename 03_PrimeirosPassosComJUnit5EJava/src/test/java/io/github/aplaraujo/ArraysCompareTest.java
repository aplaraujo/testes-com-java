package io.github.aplaraujo;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

public class ArraysCompareTest {
    @Test
    void test() {
        int[] numArr = {80, 14, 23, 34, 79};
        int[] orderedNumArr = {14, 23, 34, 79, 80};

        Arrays.sort(numArr);

        assertArrayEquals(orderedNumArr, numArr);
    }
}
