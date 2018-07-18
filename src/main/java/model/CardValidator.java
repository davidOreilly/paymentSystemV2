package model;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * Utility class containing static methods to validate various elements of a Card object
 */
public class CardValidator {

    /**
     * Check if supplied cardNumber is valid
     * @param cardNumber
     * @return true if valid. Otherwise false
     */
    public static boolean isValidCardNumber(String cardNumber) {
        char[] cardNumberChars = cardNumber.toCharArray();
        for (char cardNumberChar : cardNumberChars) {
            if (!Character.isDigit(cardNumberChar)) {
                return false;
            }
        }

        if (cardNumber.length() < 8 || cardNumber.length() > 19) {
            return false;
        }

        return true;
    }

    /**
     * Check if supplied card expiry date is valid
     * @param expiryMonth
     * @param expiryYear
     * @return true if valid. Otherwise false
     */
    public static boolean isValidExpiryDate(int expiryMonth, int expiryYear) {
        LocalDate currentDate = LocalDate.now();
        LocalDate expiryDate = LocalDate.of(expiryYear, expiryMonth, 1).with(TemporalAdjusters.lastDayOfMonth());

        if (expiryDate.isBefore(currentDate)) {
            return false;
        }

        return true;
    }

    /**
     * Check if supplied card cvv is valid
     * @param cvv
     * @return true if valid. Otherwise false
     */
    public static boolean isValidCvv(String cvv) {
        char[] cvvChars = cvv.toCharArray();
        for (char cvvChar : cvvChars) {
            if (!Character.isDigit(cvvChar)) {
                return false;
            }
        }

        if (cvv.length() != 3) {
            return false;
        }

        return true;
    }

}
