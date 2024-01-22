package com.example._02_Managing_Java_Objects.examples.execrise;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class BusinessCalculationService {
    private DataService dataService;

    public BusinessCalculationService(DataService dataService) {
        super();
        this.dataService = dataService;
    }

    public int findMax() {
        return Arrays.stream(dataService.retrieveAllData()).max().orElse(0);
    }
}
