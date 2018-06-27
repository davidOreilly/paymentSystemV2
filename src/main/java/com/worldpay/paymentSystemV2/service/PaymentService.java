package com.worldpay.paymentSystemV2.service;

import com.worldpay.paymentSystemV2.model.PaymentRequest;

/**
 * A service class to take a payment request and process it
 *
 * Note: Currently only handling one type of request but will need to think about potentially handling more
 */
public class PaymentService {

//    @Autowired
//    private PaymentDao paymentDao;

    /**
     * Persist request data to DB, send request to acquirer and respond with acquirer response
     * @param PaymentRequest the payment request
     */
    public int processPayment(PaymentRequest paymentRequest) {
        //com.worldpay.paymentSystemV2.model.Payment modelPayment = new com.worldpay.paymentSystemV2.model.Payment();

        //Payment domainPayment = new Payment();

        //First we want to persist the data received in the request
//        paymentDao.create(domainPayment);

        return 0;
    }
}
