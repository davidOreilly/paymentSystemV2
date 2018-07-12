package com.worldpay.paymentSystemV2.model;

import model.ShopperValidator;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ShopperValidatorTest {

    @Test
    public void firstNameIsValid() {
        assertTrue(ShopperValidator.isValidFirstName("Jim"));
    }

    @Test
    public void firstNameIsNotValid() {
        assertFalse(ShopperValidator.isValidFirstName("R0b3rt"));
    }
}
