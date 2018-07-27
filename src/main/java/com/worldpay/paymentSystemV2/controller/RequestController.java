package com.worldpay.paymentSystemV2.controller;

import com.worldpay.paymentSystemV2.dao.PaymentDao;
import com.worldpay.paymentSystemV2.domain.Payment;
import com.worldpay.paymentSystemV2.model.PaymentServiceRequest;
import com.worldpay.paymentSystemV2.domain.RequestFactory;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

import static model.OperationEnum.PAYMENT;
import static model.OperationEnum.REFUND;

/**
 * Controller is responsible for intercepting request from merchant,
 * converting request data to internal data,
 * sending this to model for further processing (validation, etc)
 * and getting processed data to return to merchant
 */
@Controller
public class RequestController {

    private PaymentDao paymentDao;
    private RequestFactory requestFactory;

    @Inject
    public RequestController(PaymentDao paymentDao, RequestFactory requestFactory) {
        this.paymentDao = paymentDao;
        this.requestFactory = requestFactory;
    }

    @RequestMapping(
            value = "/api/paymentService",
            method = RequestMethod.POST)
    @ResponseBody
    public String submitPaymentServiceRequest(@ApiParam(value = "", required = true) @RequestBody PaymentServiceRequest paymentServiceRequest) {
        if (paymentServiceRequest != null) {
            if (PAYMENT.getOperationName().equalsIgnoreCase(paymentServiceRequest.getOperation())) {
                Payment payment = requestFactory.createPayment(paymentServiceRequest);
                paymentDao.savePayment(payment);
            } else if (REFUND.getOperationName().equalsIgnoreCase(paymentServiceRequest.getOperation())) {
                //requestFactory.createRefund();
            } else {
                //todo probably want to throw some form of exception here as we don't recognise the operation passed to us
                return null;
            }
        }

        return null;
    }


}
