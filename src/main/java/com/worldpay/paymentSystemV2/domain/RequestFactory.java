package com.worldpay.paymentSystemV2.domain;

import com.worldpay.paymentSystemV2.model.*;
import model.CardValidator;
import model.ShopperValidator;
import org.springframework.stereotype.Component;

/**
 * Class to create Payment and Refund requests using data submitted by Merchant
 */
@Component
public class RequestFactory {

    public Payment createPayment(PaymentServiceRequest paymentServiceRequest) {
        //todo we want to notify merchant if anything is wrong with the data
        CardDetails cardDetails = paymentServiceRequest.getCardDetails();
        MerchantDetails merchantDetails = paymentServiceRequest.getMerchantDetails();
        ShopperDetails shopperDetails = paymentServiceRequest.getShopperDetails();

        Card card = createCard(cardDetails);
        Merchant merchant = createMerchant(merchantDetails);
        Shopper shopper = createShopper(shopperDetails);

        return new Payment(paymentServiceRequest.getTransactionCode(), paymentServiceRequest.getAmount(),
                paymentServiceRequest.getCurrencyCode(), card, merchant, shopper);
    }

    private Card createCard(CardDetails cardDetails) {
        String cardholderName = cardDetails.getCardholderName();
        String cardNumber = cardDetails.getCardNumber();
        int expiryMonth = cardDetails.getExpiryMonth();
        int expiryYear = cardDetails.getExpiryYear();
        String cvv = cardDetails.getCvv();

        if (!CardValidator.isValidCardNumber(cardNumber) ||
               ! CardValidator.isValidExpiryDate(expiryMonth, expiryYear)) {
            throw new IllegalArgumentException("Invalid card details");
        }

        if (cvv != null && !CardValidator.isValidCvv(cvv)) {
            throw new IllegalArgumentException("Invalid cvv");
        }

        //todo: Will need to work out which type of card we need to create here (using card bin?) rather than creating Visa every time
        Visa.VisaBuilder visaBuilder = new Visa.VisaBuilder(cardholderName, cardNumber, expiryMonth, expiryYear);

        if (cvv != null) {
            visaBuilder.withCvv(cvv);
        }

        return visaBuilder.build();
    }

    private Merchant createMerchant(MerchantDetails merchantDetails) {
        Merchant merchant = new Merchant();

        merchant.setMerchantCode(merchantDetails.getMerchantCode());

        return merchant;
    }

    private Shopper createShopper(ShopperDetails shopperDetails) {
        String firstName = shopperDetails.getFirstName();
        String lastName = shopperDetails.getLastName();
        String email = shopperDetails.getEmail();

        Address billingAddress = shopperDetails.getBillingAddress();
        String address1 = billingAddress.getLine1();
        String address2 = billingAddress.getLine2();
        String city = billingAddress.getCity();
        String state = billingAddress.getState();
        String postCode = billingAddress.getPostCode();
        //todo: Currently we do not accept spaces in phone number. Need a way of trimming these before attempting to validate
        String phone = billingAddress.getPhone();
        String countryCode = billingAddress.getCountryCode();

        if (!ShopperValidator.isValidFirstName(firstName) ||
                !ShopperValidator.isValidLastName(lastName) ||
                !ShopperValidator.isValidPostcode(postCode) ||
                !ShopperValidator.isValidCountryCode(countryCode)) {
            throw new IllegalArgumentException("Invalid shopper details");
        }

        //Create the Shopper with the mandatory parameters
        Shopper.ShopperBuilder shopperBuilder = new Shopper.ShopperBuilder(firstName, lastName, address1, postCode, countryCode);

        //Check for existence of optional parameters and set on Shopper object
        if (address2 != null) {
            shopperBuilder.withAddress2(address2);
        }

        if (city != null && ShopperValidator.isValidCity(city)) {
            shopperBuilder.withCity(city);
        } else {
            throw new IllegalArgumentException("invalid city");
        }

        if (state != null && ShopperValidator.isValidState(state)) {
            shopperBuilder.withState(state);
        } else {
            throw new IllegalArgumentException("invalid state");
        }

        if (phone != null && ShopperValidator.isValidPhone(phone)) {
            shopperBuilder.withPhone(phone);
        } else {
            throw new IllegalArgumentException("invalid phone");
        }

        if (email != null) {
            shopperBuilder.withEmail(email);
        }

        return shopperBuilder.build();
    }

}
