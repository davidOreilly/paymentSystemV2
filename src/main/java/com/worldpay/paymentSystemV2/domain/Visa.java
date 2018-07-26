package com.worldpay.paymentSystemV2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "card")
public class Visa implements Card {

    private static final String BRAND = "VISA";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String cardNumber;
    private int expiryMonth;
    private int expiryYear;
    private String cvv;
    private String cardholderName;

    private Visa(VisaBuilder builder) {
        this.cardNumber = builder.cardNumber;
        this.cardholderName = builder.cardholderName;
        this.expiryMonth = builder.expiryMonth;
        this.expiryYear = builder.expiryYear;
        this.cvv = builder.cvv;
    }

    public int getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getExpiryMonth() {
        return expiryMonth;
    }

    public int getExpiryYear() {
        return expiryYear;
    }

    public String getCvv() {
        return cvv;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public String getBrand() {
        return BRAND;
    }

    public static class VisaBuilder {
        private final String cardholderName;
        private final String cardNumber;
        private final int expiryMonth;
        private final int expiryYear;
        private String cvv;

        public VisaBuilder(String cardholderName, String cardNumber, int expiryMonth, int expiryYear) {
            this.cardholderName = cardholderName;
            this.cardNumber = cardNumber;
            this.expiryMonth = expiryMonth;
            this.expiryYear = expiryYear;
        }

        public VisaBuilder withCvv(String cvv) {
            this.cvv = cvv;
            return this;
        }

        public Visa build() {
            return new Visa(this);
        }
    }

}
