package com.example.aop.business;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.aop.annotations.TrackTime;
import com.example.aop.data.DataService2;

@Service
public class BusinessService2 {
    private DataService2 dataService2;

    public BusinessService2(DataService2 dataService2) {
        this.dataService2 = dataService2;
    }

    @TrackTime
    public List<String> sortByNathuralOrder() {
        List<String> data = dataService2.retrieveData();
        data.sort(Comparator.naturalOrder());
        return data;
    }
}
