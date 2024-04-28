package com.rishab.common;

// This is configured in src/main/java/com/rishab/config/PaymentConfig.java (demonstrating how to use third-party beans)
public class WalletGateway implements PaymentGateway {
    public WalletGateway() {
        System.out.println(getClass().getSimpleName() + " Bean created");
    }

    @Override
    public String processPayment() {
        return "Processing payment using Wallet Gateway";
    }
}
