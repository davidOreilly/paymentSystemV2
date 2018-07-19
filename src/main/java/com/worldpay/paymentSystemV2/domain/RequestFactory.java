package com.worldpay.paymentSystemV2.domain;

import com.worldpay.paymentSystemV2.model.*;
import model.CardValidator;
import model.ShopperValidator;
import org.springframework.stereotype.Component;

import java.util.Optional;

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

    public String createRefund() {
        return null;
    }

    private Card createCard(CardDetails cardDetails) {
        String cardNumber = cardDetails.getCardNumber();
        int expiryMonth = cardDetails.getExpiryMonth();
        int expiryYear = cardDetails.getExpiryYear();
        String cvv = cardDetails.getCvv();
        String cardholderName = cardDetails.getCardholderName();

        if (isCardDetailsValid(cardNumber, expiryMonth, expiryYear)) {
            Visa visaCard = new Visa();

            visaCard.setCardNumber(cardNumber);
            visaCard.setExpiryMonth(expiryMonth);
            visaCard.setExpiryYear(expiryYear);
            visaCard.setCvv(cvv);
            visaCard.setCardholderName(cardholderName);

            return visaCard;
        }

        return null;
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
        String phone = billingAddress.getPhone();
        String countryCode = billingAddress.getCountryCode();

        if (isShopperDetailsValid(firstName, lastName, postCode, phone, countryCode)) {
            return new Shopper.ShopperBuilder(firstName, lastName, address1, postCode, countryCode)
                    .withAddress2(address2)
                    .withCity(city)
                    .withState(state)
                    .withPhone(phone)
                    .withEmail(email)
                    .build();
        }

        return null;
    }

    private boolean isCardDetailsValid(String cardNumber, int expiryMonth, int expiryYear) {
        //todo will need to include cvv validation but in order to do so
        //todo will need to incorporate multiple card types (Visa, Mastercard, etc)
//      String cardBrand = card.getBrand();
//        if ("VISA".equals(cardBrand)) {
//            if (CardValidator.isValidCvv())
//        }

        if (CardValidator.isValidCardNumber(cardNumber) &&
            CardValidator.isValidExpiryDate(expiryMonth, expiryYear)) {
                return true;
        }

        return false;
    }

    private boolean isShopperDetailsValid(String firstName, String lastName, String postCode, String phone, String countryCode) {
        if (ShopperValidator.isValidFirstName(firstName) &&
                ShopperValidator.isValidLastName(lastName) &&
                ShopperValidator.isValidPostcode(postCode) &&
                ShopperValidator.isValidPhone(phone) &&
                ShopperValidator.isValidCountryCode(countryCode)) {
            return true;
        }

        return false;
    }

}
