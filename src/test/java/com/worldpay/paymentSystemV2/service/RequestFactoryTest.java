package com.worldpay.paymentSystemV2.service;

import com.worldpay.paymentSystemV2.domain.Card;
import com.worldpay.paymentSystemV2.domain.Merchant;
import com.worldpay.paymentSystemV2.domain.Payment;
import com.worldpay.paymentSystemV2.domain.Shopper;
import com.worldpay.paymentSystemV2.domain.Visa;
import com.worldpay.paymentSystemV2.model.Address;
import com.worldpay.paymentSystemV2.model.CardDetails;
import com.worldpay.paymentSystemV2.model.MerchantDetails;
import com.worldpay.paymentSystemV2.model.PaymentServiceRequest;
import com.worldpay.paymentSystemV2.model.ShopperDetails;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class RequestFactoryTest {

    private static final String ADDRESS_LINE_1 = "1 Smith Lane";
    private static final String ADDRESS_LINE_2 = "Milton";
    private static final String CARD_NUMBER = "4444333322221111";
    private static final String COUNTY = "Cambridgeshire";
    private static final String FIRST_NAME = "Jim";
    private static final String LAST_NAME = "Smith";
    private static final String CARDHOLDER_NAME = FIRST_NAME + " " + LAST_NAME;
    private static final int COUNTRY_CODE = 840;
    private static final int CVV = 896;
    private static final int EXPIRY_MONTH = 10;
    private static final int EXPIRY_YEAR = 2020;
    private static final String MERCHANT_CODE = "MYMERCHANT01";
    private static final String PHONE_NUMBER = "01233 568745";
    private static final String POSTCODE = "CB4 0WE";
    private static final String TRANSACTION_CODE = "123456ABC";
    private static final String TOWN = "Cambridge";
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

        this.requestFactory = new RequestFactory();

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
        when(address.getCity()).thenReturn(TOWN);
        when(address.getState()).thenReturn(COUNTY);
        when(address.getPostCode()).thenReturn(POSTCODE);
        when(address.getPhone()).thenReturn(PHONE_NUMBER);

        when(shopperDetails.getFirstName()).thenReturn(FIRST_NAME);
        when(shopperDetails.getLastName()).thenReturn(LAST_NAME);
        when(shopperDetails.getEmail()).thenReturn("jimsmith@mymail.com");
    }

    @Test
    public void paymentRequestCreatedWithExpectedValues() throws Exception {
        Payment payment = requestFactory.createPaymentRequest(paymentServiceRequest);
        Payment expectedPayment = createPayment();


    }

    private Payment createPayment() {
        Card card = createCard();
        Merchant merchant = createMerchant();
        Shopper shopper = createShopper();

        Payment payment = new Payment(TRANSACTION_CODE, AMOUNT, CURRENCY_CODE, card, merchant, shopper);
        payment.setId(123);

        return payment;
    }

    private Card createCard() {
        Visa card = new Visa();

        card.setCardholderName(CARDHOLDER_NAME);
        card.setCardNumber(CARD_NUMBER);
        card.setExpiryMonth(EXPIRY_MONTH);
        card.setExpiryYear(EXPIRY_YEAR);
        card.setCvv(CVV);

        return card;
    }

    private Merchant createMerchant() {
        Merchant merchant = new Merchant();

        merchant.setMerchantCode(MERCHANT_CODE);

        return merchant;
    }

    private Shopper createShopper() {
        Shopper shopper = new Shopper();

        shopper.setFirstName(FIRST_NAME);
        shopper.setLastName(LAST_NAME);
        shopper.setAddress1(ADDRESS_LINE_1);
        shopper.setAddress2(ADDRESS_LINE_2);
        shopper.setTown(TOWN);
        shopper.setCounty(COUNTY);
        shopper.setPostcode(POSTCODE);
        shopper.setCountryCode(COUNTRY_CODE);
        shopper.setPhone(PHONE_NUMBER);
    }
}
