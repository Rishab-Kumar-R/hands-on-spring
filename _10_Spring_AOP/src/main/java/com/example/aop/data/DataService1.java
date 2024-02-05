package com.example.aop.data;

import org.springframework.stereotype.Repository;

@Repository
public class DataService1 {
    public int[] retrieveData() {
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new int[] { 62, 5, 34, 7, 45, 32, 4, 99, 8, 0 };
    }
}
