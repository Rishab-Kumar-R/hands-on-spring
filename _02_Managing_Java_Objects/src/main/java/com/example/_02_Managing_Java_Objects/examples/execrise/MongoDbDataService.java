package com.example._02_Managing_Java_Objects.examples.execrise;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class MongoDbDataService implements DataService {
    @Override
    public int[] retrieveAllData() {
        return new int[]{1, 2, 3, 4, 5};
    }
}
