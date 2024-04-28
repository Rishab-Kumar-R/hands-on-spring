package com.rishab.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class AmazonPayGateway implements PaymentGateway {
    public AmazonPayGateway() {
        System.out.println(getClass().getSimpleName() + " Bean created");
    }

    // Bean Lifecycle Methods
    @PostConstruct
    public void init() {
        System.out.println(getClass().getSimpleName() + " Bean initialized");
    }

    @PreDestroy
    public void destroy() {
        System.out.println(getClass().getSimpleName() + " Bean will destroy soon");
    }

    @Override
    public String processPayment() {
        return "Processing payment using AmazonPay Gateway";
    }
}
