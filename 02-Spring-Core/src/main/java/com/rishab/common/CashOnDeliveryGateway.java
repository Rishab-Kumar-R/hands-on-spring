package com.rishab.common;

import org.springframework.stereotype.Component;

@Component
public class CashOnDeliveryGateway implements PaymentGateway {
    public CashOnDeliveryGateway() {
        System.out.println(getClass().getSimpleName() + " Bean created");
    }

    @Override
    public String processPayment() {
        return "Processing payment using CashOnDelivery Gateway";
    }
}
