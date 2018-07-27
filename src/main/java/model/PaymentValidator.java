package model;

/**
 * Utility class containing static methods to validate various elements of a Payment object
 */
public class PaymentValidator {

    private PaymentValidator() {
    }

    /**
     * Check if supplied currencyCode is a valid 2 digit ISO 4217 currency code
     * @param currencyCode
     * @return true if valid. Otherwise false
     */
    public static boolean isValidCurrencyCode(String currencyCode) {
       return currencyCode.matches("[A-Za-z]{3}");
    }
}
