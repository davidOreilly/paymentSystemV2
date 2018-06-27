package com.worldpay.paymentSystemV2.controllers;

import com.worldpay.paymentSystemV2.dao.PaymentDao;
import com.worldpay.paymentSystemV2.domain.Merchant;
import com.worldpay.paymentSystemV2.domain.Payment;
import com.worldpay.paymentSystemV2.domain.Shopper;
import com.worldpay.paymentSystemV2.domain.Visa;
import com.worldpay.paymentSystemV2.model.CardDetails;
import com.worldpay.paymentSystemV2.model.MerchantDetails;
import com.worldpay.paymentSystemV2.model.PaymentServiceRequest;
import com.worldpay.paymentSystemV2.model.ShopperDetails;
import com.worldpay.paymentSystemV2.service.RequestFactory;
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

    public static final String PAYMENT_REQUEST = "PAYMENT";
    public static final String REFUND_REQUEST = "REFUND";

    @Autowired
    private PaymentDao paymentDao;
    @Autowired
    private RequestFactory requestFactory;

    @RequestMapping(
            value = "/api/paymentService",
            method = RequestMethod.POST)
    @ResponseBody
    public String submitPaymentServiceRequest(@ApiParam(value = "", required = true) @RequestBody PaymentServiceRequest paymentServiceRequest) {
        if (paymentServiceRequest != null) {
            if (PAYMENT_REQUEST.equals(paymentServiceRequest.getOperation())) {
                Payment payment = requestFactory.createPaymentRequest(paymentServiceRequest);
            } else if (REFUND_REQUEST.equals(paymentServiceRequest.getOperation())) {
                requestFactory.createRefundRequest();
            } else {
                //todo probably want to throw some form of exception here as we don't recognise the operation passed to us
                return null;
            }
        }

        //paymentDao.create(payment, visaCard, merchant, shopper);

        return null;
    }


}
