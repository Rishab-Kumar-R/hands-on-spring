package com.example.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataServiceStub implements DataService {
    @Override
    public int[] retrieveAllData() {
        return new int[]{24, 15, 3};
    }
}

public class SomeBusinessImplStubTest {
    @Test
    public void testFindTheGreatestFromAllData() {
        DataService dataServiceStub = new DataServiceStub();
        SomeBusinessImpl business = new SomeBusinessImpl(dataServiceStub);
        int result = business.findTheGreatestFromAllData();
        assertEquals(24, result);
    }
}
