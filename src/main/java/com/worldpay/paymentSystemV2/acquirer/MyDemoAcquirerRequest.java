package com.worldpay.paymentSystemV2.acquirer;

import demoPaymentService.acquirer.DemoAcquirerRequest;

import java.time.YearMonth;

public class MyDemoAcquirerRequest implements DemoAcquirerRequest {
    private long amount;
    private String cardNumber;
    private YearMonth expiryDate;
    private String cvc;
    private String cardholderName;

    public Long getAmount() {
        return amount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public YearMonth getExpiryDate() {
        return expiryDate;
    }

    public String getCvc() {
        return cvc;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpiryDate(YearMonth expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

}
