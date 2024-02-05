package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathTest {
    private final Math math = new Math();

    @Test
    void testCalculateSumEmptyArray() {
        assertEquals(0, math.calculateSum(new int[0]), "The sum of an empty array should be 0");
    }

    @Test
    void testCalculateSum() {
        assertEquals(15, math.calculateSum(new int[]{1, 2, 3, 4, 5}), "The sum of 1 to 5 should be 15");
    }
}
