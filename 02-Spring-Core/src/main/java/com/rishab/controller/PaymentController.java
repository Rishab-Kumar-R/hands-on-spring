package com.rishab.controller;

import com.rishab.common.PaymentGateway;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    private final PaymentGateway stripeGateway;
    private final PaymentGateway anotherStripeGateway;
    private final PaymentGateway walletGateway;

    // @Qualifier >>> @Primary (as AmazonPayGateway is annotated with @Primary StripeGateway will be injected)
    public PaymentController(
        @Qualifier("stripeGateway") PaymentGateway stripeGateway,
        @Qualifier("stripeGateway") PaymentGateway anotherStripeGateway,
        @Qualifier("wallet") PaymentGateway walletGateway) {
        this.stripeGateway = stripeGateway;
        this.anotherStripeGateway = anotherStripeGateway;
        this.walletGateway = walletGateway;
    }

    @GetMapping("/pay")
    public String pay() {
        return walletGateway.processPayment();
    }

    @GetMapping("/checkout")
    public String checkout() {
        return "Comparing beans: stripeGateway == anotherStripeGateway: " + (stripeGateway == anotherStripeGateway);
    }
}
