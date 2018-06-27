package com.worldpay.paymentSystemV2.controllers;

import com.worldpay.paymentSystemV2.dao.PaymentDao;
import com.worldpay.paymentSystemV2.domain.Merchant;
import com.worldpay.paymentSystemV2.domain.Payment;
import com.worldpay.paymentSystemV2.domain.Shopper;
import com.worldpay.paymentSystemV2.domain.Visa;
import com.worldpay.paymentSystemV2.model.CardDetails;
import com.worldpay.paymentSystemV2.model.MerchantDetails;
import com.worldpay.paymentSystemV2.model.PaymentRequest;
import com.worldpay.paymentSystemV2.model.ShopperDetails;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller is responsible for intercepting request from merchant,
 * converting request data to internal data,
 * sending this to model for further processing (validation, etc)
 * and getting processed data to return to merchant
 */
@Controller
public class RequestController {

    @Autowired
    private PaymentDao paymentDao;

    @RequestMapping(
            value = "/api/submitPayment",
            method = RequestMethod.POST)
    @ResponseBody
    public String submitPayment(@ApiParam(value = "", required = true) @RequestBody PaymentRequest paymentRequest) {
        CardDetails cardDetails = paymentRequest.getCardDetails();
        MerchantDetails merchantDetails = paymentRequest.getMerchantDetails();
        ShopperDetails shopperDetails = paymentRequest.getShopperDetails();

        Visa visaCard = new Visa();
        visaCard.setCardNumber(cardDetails.getCardNumber());
        visaCard.setExpiryMonth(String.valueOf(cardDetails.getExpiryMonth()));
        visaCard.setExpiryYear(String.valueOf(cardDetails.getExpiryYear()));
        visaCard.setCvv(String.valueOf(cardDetails.getCvv()));
        visaCard.setCardholderName(cardDetails.getCardholderName());

        Merchant merchant = new Merchant();
        merchant.setMerchantCode(merchantDetails.getMerchantCode());

        Shopper shopper = new Shopper();
        shopper.setFirstName(shopperDetails.getFirstName());
        shopper.setLastName(shopperDetails.getLastName());
        shopper.setAddress1(shopperDetails.getBillingAddress().getLine1());
        shopper.setAddress2(shopperDetails.getBillingAddress().getLine2());
        shopper.setTown(shopperDetails.getBillingAddress().getCity());
        shopper.setCounty(shopperDetails.getBillingAddress().getState());
        shopper.setCountryCode(Integer.valueOf(shopperDetails.getBillingAddress().getCountryCode()));
        shopper.setPostcode(shopperDetails.getBillingAddress().getPostCode());
        shopper.setPhone(shopperDetails.getBillingAddress().getPhone());

        Payment payment = new Payment(paymentRequest.getTransactionCode(), paymentRequest.getAmount(), paymentRequest.getCurrencyCode());

        //Todo make a call to validate the request data we've received before persisting to DB
//        if (requestCard == null || requestMerchant == null || requestOrder == null || requestShopper == null) {
        //Todo we want to notify merchant that data is missing from the request
        //return null;
//        } else {
//            Payment payment = new Payment(requestCard, requestMerchant, requestOrder, requestShopper);


        paymentDao.create(payment, visaCard, merchant, shopper);

        return null;
    }

//        return null;


    @RequestMapping(
            value="/api/submitRefund",
            method = RequestMethod.POST)
    @ResponseBody
    public String submitRefund(@ApiParam(value = "", required = true) @RequestBody PaymentRequest paymentRequest) {
        return null;
    }
}
