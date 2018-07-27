package com.worldpay.paymentSystemV2.model;

import model.ShopperValidator;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ShopperValidatorTest {

    @Test
    public void firstNameContainingOnlyLettersIsValid() {
        assertTrue(ShopperValidator.isValidFirstName("Jim"));
    }

    @Test
    public void firstNameIsNotValid() {
        assertFalse(ShopperValidator.isValidFirstName("R0b3rt"));
    }

    @Test
    public void lastNameContainingOnlyLettersIsValid() {
        assertTrue(ShopperValidator.isValidLastName("Smith"));
    }

    @Test
    public void lastNameIsNotValid() {
        assertFalse(ShopperValidator.isValidLastName("J0ne5"));
    }

    @Test
    public void postcodeWithSpaceIsValid() {
        assertTrue(ShopperValidator.isValidPostcode("CB4 0WE"));
    }

    @Test
    public void postcodeWithoutSpaceIsValid() {
        assertTrue(ShopperValidator.isValidPostcode("CB10WF"));
    }

    @Test
    public void postcodeEndingWithNumbersIsInvalid() {
        assertFalse(ShopperValidator.isValidPostcode("EC1A 123"));
    }

    @Test
    public void phoneContainingOnlyNumbersIsValid() {
        assertTrue(ShopperValidator.isValidPhone("01755660258"));
    }

    @Test
    public void phoneNumberWithLettersIsInvalid() {
        assertFalse(ShopperValidator.isValidPhone("0177Five660257"));
    }

    @Test
    public void twoLetterCountryCodeContainingOnlyLettersIsValid() {
        assertTrue(ShopperValidator.isValidCountryCode("GB"));
    }

    @Test
    public void countryCodeContainingNumbersIsInvalid() {
        assertFalse(ShopperValidator.isValidCountryCode("G1"));
    }

    @Test
    public void countryCodeWithLengthLessThanTwoIsInvalid() {
        assertFalse(ShopperValidator.isValidCountryCode("P"));
    }

    @Test
    public void countryCodeWithLengthLongerThanTwoIsInvalid() {
        assertFalse(ShopperValidator.isValidCountryCode("USA"));
    }

}
