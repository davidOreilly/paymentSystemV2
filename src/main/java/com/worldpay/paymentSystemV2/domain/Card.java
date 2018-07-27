package com.worldpay.paymentSystemV2.domain;

public interface Card {

    public String getCardNumber();

    public int getExpiryMonth();

    public int getExpiryYear();

    public String getCardholderName();

    public String getBrand();
}
