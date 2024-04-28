package com.rishab.service;

import org.springframework.stereotype.Service;

@Service
public class TrafficFortuneServiceImpl implements TrafficFortuneService {
    @Override
    public String getFortune() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Expect heavy traffic this morning";
    }

    @Override
    public String getFortune(boolean b) {
        if (b) {
            throw new RuntimeException("Major accident! Highway is closed!");
        }
        return getFortune();
    }
}
