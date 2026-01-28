package io.github.aplaraujo.math;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class RandomTest {

    @Test
    void testA() {
        System.out.println("Test A is running!");
    }

    @Test
    void testB() {
        System.out.println("Test B is running!");
    }

    @Test
    void testC() {
        System.out.println("Test C is running!");
    }

    @Test
    void testD() {
        System.out.println("Test D is running!");
    }
}
