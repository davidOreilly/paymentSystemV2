package com.worldpay.paymentSystemV2.service;

import com.worldpay.paymentSystemV2.domain.*;
import com.worldpay.paymentSystemV2.model.Address;
import com.worldpay.paymentSystemV2.model.CardDetails;
import com.worldpay.paymentSystemV2.model.MerchantDetails;
import com.worldpay.paymentSystemV2.model.PaymentServiceRequest;
import com.worldpay.paymentSystemV2.model.ShopperDetails;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class RequestFactoryTest {

    private static final String ADDRESS_LINE_1 = "1 Smith Lane";
    private static final String ADDRESS_LINE_2 = "Milton";
    private static final String CARD_NUMBER = "4444333322221111";
    private static final String STATE = "Cambridgeshire";
    private static final String FIRST_NAME = "Jim";
    private static final String LAST_NAME = "Smith";
    private static final String CARDHOLDER_NAME = FIRST_NAME + " " + LAST_NAME;
    private static final String COUNTRY_CODE = "GB";
    private static final String CVV = "896";
    private static final int EXPIRY_MONTH = 10;
    private static final int EXPIRY_YEAR = 2020;
    private static final String MERCHANT_CODE = "MYMERCHANT01";
    private static final String PHONE_NUMBER = "01233 568745";
    private static final String EMAIL = "Jsmith@mymail.com";
    private static final String POSTCODE = "CB4 0WE";
    private static final String TRANSACTION_CODE = "123456ABC";
    private static final String CITY = "Cambridge";
    private static final int AMOUNT = 100;
    private static final String CURRENCY_CODE = "GBP";

    @Mock private PaymentServiceRequest paymentServiceRequest;
    @Mock private CardDetails cardDetails;
    @Mock private MerchantDetails merchantDetails;
    @Mock private Address address;
    @Mock private ShopperDetails shopperDetails;

    private RequestFactory requestFactory;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        requestFactory = new RequestFactory();

        when(paymentServiceRequest.getOperation()).thenReturn("PAYMENT");
        when(paymentServiceRequest.getAmount()).thenReturn(AMOUNT);
        when(paymentServiceRequest.getCurrencyCode()).thenReturn(CURRENCY_CODE);
        when(paymentServiceRequest.getTransactionCode()).thenReturn(TRANSACTION_CODE);
        when(paymentServiceRequest.getCardDetails()).thenReturn(cardDetails);
        when(paymentServiceRequest.getMerchantDetails()).thenReturn(merchantDetails);
        when(paymentServiceRequest.getShopperDetails()).thenReturn(shopperDetails);
        when(shopperDetails.getBillingAddress()).thenReturn(address);

        when(cardDetails.getCardNumber()).thenReturn(CARD_NUMBER);
        when(cardDetails.getExpiryMonth()).thenReturn(EXPIRY_MONTH);
        when(cardDetails.getExpiryYear()).thenReturn(EXPIRY_YEAR);
        when(cardDetails.getCvv()).thenReturn(CVV);
        when(cardDetails.getCardholderName()).thenReturn(CARDHOLDER_NAME);

        when(merchantDetails.getMerchantCode()).thenReturn(MERCHANT_CODE);

        when(address.getFirstName()).thenReturn(FIRST_NAME);
        when(address.getLastName()).thenReturn(LAST_NAME);
        when(address.getLine1()).thenReturn(ADDRESS_LINE_1);
        when(address.getLine2()).thenReturn(ADDRESS_LINE_2);
        when(address.getCity()).thenReturn(CITY);
        when(address.getState()).thenReturn(STATE);
        when(address.getPostCode()).thenReturn(POSTCODE);
        when(address.getPhone()).thenReturn(PHONE_NUMBER);
        when(address.getCountryCode()).thenReturn(COUNTRY_CODE);

        when(shopperDetails.getFirstName()).thenReturn(FIRST_NAME);
        when(shopperDetails.getLastName()).thenReturn(LAST_NAME);
        when(shopperDetails.getEmail()).thenReturn(EMAIL);
    }

    @Test
    public void paymentRequestCreatedWithExpectedPaymentValues() throws Exception {
        Payment payment = requestFactory.createPayment(paymentServiceRequest);

        assertEquals(TRANSACTION_CODE, payment.getTransactionCode());
        assertEquals(AMOUNT, payment.getAmount());
        assertEquals(CURRENCY_CODE, payment.getCurrencyCode());
    }

    @Test
    public void paymentRequestCreatedWithExpectedCardValues() throws Exception {
        Payment payment = requestFactory.createPayment(paymentServiceRequest);
        Card card = payment.getCard();

        assertEquals(CARD_NUMBER, card.getCardNumber());
        assertEquals(CARDHOLDER_NAME, card.getCardholderName());
        assertEquals(EXPIRY_MONTH, card.getExpiryMonth());
        assertEquals(EXPIRY_YEAR, card.getExpiryYear());
    }

    @Test
    public void paymentRequestCreatedWithExpectedMerchantValues() throws Exception {
        Payment payment = requestFactory.createPayment(paymentServiceRequest);
        Merchant merchant = payment.getMerchant();

        assertEquals(MERCHANT_CODE, merchant.getMerchantCode());
    }

    @Test
    public void paymentRequestCreatedWithExpectedShopperValues() throws Exception {
        Payment payment = requestFactory.createPayment(paymentServiceRequest);
        Shopper shopper = payment.getShopper();

        assertEquals(FIRST_NAME, shopper.getFirstName());
        assertEquals(LAST_NAME, shopper.getLastName());
        assertEquals(ADDRESS_LINE_1, shopper.getAddress1());
        assertEquals(ADDRESS_LINE_2, shopper.getAddress2());
        assertEquals(CITY, shopper.getCity());
        assertEquals(STATE, shopper.getState());
        assertEquals(POSTCODE, shopper.getPostCode());
        assertEquals(COUNTRY_CODE, shopper.getCountryCode());
        assertEquals(PHONE_NUMBER, shopper.getPhone());
        assertEquals(EMAIL, shopper.getEmail());
    }

}
