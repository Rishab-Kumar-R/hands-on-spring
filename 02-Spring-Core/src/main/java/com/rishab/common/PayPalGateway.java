package com.rishab.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.PaymentUtil;

@Component
public class PayPalGateway implements PaymentGateway {
    private PaymentUtil paymentUtil;

    public PayPalGateway() {
        System.out.println(getClass().getSimpleName() + " Bean created");
    }

    // setter injection (the method name can be anything)
    @Autowired
    public void setPaymentUtil(PaymentUtil paymentUtil) {
        this.paymentUtil = paymentUtil;
    }

    @Override
    public String processPayment() {
        if (paymentUtil == null) {
            throw new RuntimeException("PaymentUtil is not set");
        }
        return "Processing payment using PayPal Gateway. " + paymentUtil.isValidCardNumber() + " " + paymentUtil.isValidCvv();
    }
}
