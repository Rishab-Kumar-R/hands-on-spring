package com.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AssertTest {
    @BeforeAll
    static void init() {
        System.out.println("Before all tests");
    }

    @BeforeEach
    void setup() {
        System.out.println("Before each test");
    }

    List<String> todos = Arrays.asList("Learn Spring", "Learn Spring Boot", "Learn Docker");

    @Test
    void test() {
        boolean test = todos.contains("Learn Spring");
        boolean test2 = todos.contains("Learn Kubernetes");

        assertTrue(test);
        assertEquals(3, todos.size());
        assertFalse(test2);
        assertArrayEquals(new String[]{"Learn Spring", "Learn Spring Boot", "Learn Docker"}, todos.toArray());
        System.out.println("Test 1");
    }

    @Test
    void test2() {
        System.out.println("Test 2");
    }

    @Test
    void test3() {
        System.out.println("Test 3");
    }

    @AfterEach
    void tearDown() {
        System.out.println("After each test");
    }

    @AfterAll
    static void done() {
        System.out.println("After all tests");
    }
}
