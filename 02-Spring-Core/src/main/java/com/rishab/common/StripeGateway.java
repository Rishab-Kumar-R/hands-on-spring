package com.rishab.common;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// @Lazy annotation is used to delay the creation of the bean until it is actually needed
@Component
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StripeGateway implements PaymentGateway {
    public StripeGateway() {
        System.out.println(getClass().getSimpleName() + " Bean created");
    }

    @Override
    public String processPayment() {
        return "Processing payment using Stripe Gateway";
    }
}
