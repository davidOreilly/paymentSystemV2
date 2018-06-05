package com.worldpay.paymentSystemV2.api;

import com.worldpay.paymentSystemV2.model.PaymentRequest;
import com.worldpay.paymentSystemV2.model.PaymentResponse;
import com.worldpay.paymentSystemV2.service.PaymentService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
public class SubmitPaymentController {

    @Autowired
    private final PaymentService paymentService;

    @Inject
    public SubmitPaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "api/submitPayment")
    public ResponseEntity<PaymentResponse> submitPayment(@ApiParam(value = "", required = true) @RequestBody PaymentRequest request) {
        System.out.println(paymentService.processPayment(request));

        return null;
    }

//    @RequestMapping(method = RequestMethod.POST, value = "api/submitPayment")
//    public String submitPayment() {
//        return "Hello world";
//    }

}
