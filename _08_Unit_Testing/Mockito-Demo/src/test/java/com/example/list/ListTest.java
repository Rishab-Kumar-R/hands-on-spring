package com.example.list;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTest {
    @Test
    public void test() {
        List<?> listMock = mock(List.class);
        when(listMock.size()).thenReturn(10);
        assertEquals(10, listMock.size());
    }

    @Test
    public void multipleReturns() {
        List<?> listMock = mock(List.class);
        when(listMock.size()).thenReturn(10).thenReturn(20);
        assertEquals(10, listMock.size());
        assertEquals(20, listMock.size());
    }

    @Test
    public void parameters() {
        List<String> listMock = mock(List.class);
        when(listMock.getFirst()).thenReturn("first");
        assertEquals("first", listMock.getFirst());
        assertNull(listMock.get(1));
    }

    @Test
    public void genericParameters() {
        List listMock = mock(List.class);
        when(listMock.get(Mockito.anyInt())).thenReturn("first element");
        assertEquals("first element", listMock.get(0));
    }
}
