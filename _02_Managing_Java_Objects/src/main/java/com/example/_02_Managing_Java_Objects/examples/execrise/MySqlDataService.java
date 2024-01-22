package com.example._02_Managing_Java_Objects.examples.execrise;

import org.springframework.stereotype.Repository;

@Repository
public class MySqlDataService implements DataService {
    @Override
    public int[] retrieveAllData() {
        return new int[]{11, 22, 33, 44, 55};
    }
}
