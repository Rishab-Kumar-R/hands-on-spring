package com.rishab.config;

import com.rishab.common.PaymentGateway;
import com.rishab.common.WalletGateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfig {
    @Bean("wallet")
    public PaymentGateway walletGateway() {
        return new WalletGateway();
    }
}
