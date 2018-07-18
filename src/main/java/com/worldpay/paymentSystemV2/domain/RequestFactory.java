package com.worldpay.paymentSystemV2.domain;

import com.worldpay.paymentSystemV2.model.CardDetails;
import com.worldpay.paymentSystemV2.model.MerchantDetails;
import com.worldpay.paymentSystemV2.model.PaymentServiceRequest;
import com.worldpay.paymentSystemV2.model.ShopperDetails;
import model.CardValidator;
import org.springframework.stereotype.Component;

/**
 * Class to create Payment and Refund requests using data submitted by Merchant
 */
@Component
public class RequestFactory {

    public Payment createPayment(PaymentServiceRequest paymentServiceRequest) {
        //todo we will want to validate this data somewhere before we start creating domain objects from it
        //todo we also want to notify merchant if anything is wrong with the data
        CardDetails cardDetails = paymentServiceRequest.getCardDetails();
        MerchantDetails merchantDetails = paymentServiceRequest.getMerchantDetails();
        ShopperDetails shopperDetails = paymentServiceRequest.getShopperDetails();



        Card visaCard = createCard(cardDetails);
        Merchant merchant = createMerchant(merchantDetails);
        Shopper shopper = createShopper(shopperDetails);

        return new Payment(paymentServiceRequest.getTransactionCode(), paymentServiceRequest.getAmount(),
                paymentServiceRequest.getCurrencyCode(), visaCard, merchant, shopper);
    }

    public String createRefund() {
        return null;
    }

    private Card createCard(CardDetails cardDetails) {
        Visa visaCard = new Visa();

        visaCard.setCardNumber(cardDetails.getCardNumber());
        visaCard.setExpiryMonth(cardDetails.getExpiryMonth());
        visaCard.setExpiryYear(cardDetails.getExpiryYear());
        visaCard.setCvv(cardDetails.getCvv());
        visaCard.setCardholderName(cardDetails.getCardholderName());

        return visaCard;
    }

    private Merchant createMerchant(MerchantDetails merchantDetails) {
        Merchant merchant = new Merchant();

        merchant.setMerchantCode(merchantDetails.getMerchantCode());

        return merchant;
    }

    private Shopper createShopper(ShopperDetails shopperDetails) {
        Shopper shopper = new Shopper();

        shopper.setFirstName(shopperDetails.getFirstName());
        shopper.setLastName(shopperDetails.getLastName());
        shopper.setAddress1(shopperDetails.getBillingAddress().getLine1());
        shopper.setAddress2(shopperDetails.getBillingAddress().getLine2());
        shopper.setTown(shopperDetails.getBillingAddress().getCity());
        shopper.setCounty(shopperDetails.getBillingAddress().getState());
        shopper.setCountryCode(shopperDetails.getBillingAddress().getCountryCode());
        shopper.setPostcode(shopperDetails.getBillingAddress().getPostCode());
        shopper.setPhone(shopperDetails.getBillingAddress().getPhone());

        return shopper;
    }

    private boolean areCardDetailsValid(Card cardDetails) {
        String cardNumber = cardDetails.getCardNumber();
        int expiryMonth = cardDetails.getExpiryMonth();
        int expiryYear = cardDetails.getExpiryYear();
        String cardBrand = cardDetails.getBrand();

        if ("VISA".equals(cardBrand)) {
            if (CardValidator.isValidCvv())
        }

        if (CardValidator.isValidCardNumber(cardNumber) &&
            CardValidator.isValidExpiryDate(expiryMonth, expiryYear) &&
            CardValidator.isValidCvv(cardDetails.getBrand())) {
                return true;
        }

        return false;
    }

}
