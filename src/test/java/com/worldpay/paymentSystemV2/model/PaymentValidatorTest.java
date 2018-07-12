package com.worldpay.paymentSystemV2.model;

import model.PaymentValidator;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PaymentValidatorTest {

    @Test
    public void currencyCodeIsValid() {
        assertTrue(PaymentValidator.isValidCurrencyCode("GBP"));
    }

    @Test
    public void currencyCodeIsNotValid() {
        assertFalse(PaymentValidator.isValidCurrencyCode("GB1"));
    }
}
