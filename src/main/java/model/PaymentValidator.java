package model;

public class PaymentValidator {

    private PaymentValidator() {
    }

    public static boolean isValidCurrencyCode(String currencyCode) {
       return currencyCode.matches("[A-Za-z]{3}");
    }
}
