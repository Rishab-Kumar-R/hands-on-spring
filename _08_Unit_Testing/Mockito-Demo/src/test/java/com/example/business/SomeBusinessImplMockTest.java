package com.example.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SomeBusinessImplMockTest {
    @Mock
    private DataService dataServiceMock;

    @InjectMocks
    private SomeBusinessImpl business;

    @Test
    public void testFindTheGreatestFromAllData() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{24, 15, 3});
        assertEquals(24, business.findTheGreatestFromAllData());
    }

    @Test
    public void testFindTheGreatestFromAllDataForOneValue() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{15});
        assertEquals(15, business.findTheGreatestFromAllData());
    }

    @Test
    public void testFindTheGreatestFromAllDataNoValues() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{});
        assertEquals(Integer.MIN_VALUE, business.findTheGreatestFromAllData());
    }
}
