package com.worldpay.paymentSystemV2.model;

import model.CardValidator;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CardValidatorTest {

    @Test
    public void cardNumberContainingOnlyNumbersIsValid() throws Exception {
        assertTrue(CardValidator.isValidCardNumber("123456789012"));
    }

    @Test
    public void cardNumberContainingLettersIsInvalid() throws Exception {
        assertFalse(CardValidator.isValidCardNumber("123FourFive678"));
    }

    @Test
    public void cardNumberLessThanEightDigitsIsInvalid() throws Exception {
        assertFalse(CardValidator.isValidCardNumber("0123456"));
    }

    @Test
    public void cardNumberMoreThanNineteenDigitsIsInvalid() throws Exception {
        assertFalse(CardValidator.isValidCardNumber("01234567890121346789012345"));
    }

    @Test
    public void expiryDateAfterCurrentDateIsValid() throws Exception {
        int expiryMonth = 10;
        int expiryYear = 2020;

        assertTrue(CardValidator.isValidExpiryDate(expiryMonth, expiryYear));
    }

    @Test
    public void expiryDateBeforeCurrentDateIsInvalid() throws Exception {
        int expiryMonth = 2;
        int expiryYear = 2015;

        assertFalse(CardValidator.isValidExpiryDate(expiryMonth, expiryYear));
    }
}
