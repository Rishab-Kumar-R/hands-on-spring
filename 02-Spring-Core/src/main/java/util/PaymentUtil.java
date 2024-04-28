package util;

import org.springframework.stereotype.Component;

@Component
public class PaymentUtil {
    public String isValidCardNumber() {
        return "Stimulating card number validation...";
    }

    public String isValidCvv() {
        return "Stimulating CVV validation...";
    }
}
