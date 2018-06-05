package com.worldpay.paymentSystemV2.service;

import com.worldpay.paymentSystemV2.acquirer.MyDemoAcquirerRequest;
import com.worldpay.paymentSystemV2.model.Card;
import com.worldpay.paymentSystemV2.model.Customer;
import com.worldpay.paymentSystemV2.model.PaymentRequest;
import com.worldpay.paymentSystemV2.model.Purchase;
import demoPaymentService.acquirer.DemoAcquirer;
import demoPaymentService.acquirer.DemoAcquirerImpl;
import demoPaymentService.acquirer.DemoAcquirerResponse;
import org.springframework.stereotype.Component;

import java.time.YearMonth;

@Component
public class PaymentService {

    public int processPayment(PaymentRequest paymentRequest) {
        MyDemoAcquirerRequest acquirerRequest = new MyDemoAcquirerRequest();

        Purchase purchase = paymentRequest.getPurchase();
        Customer customer = paymentRequest.getCustomer();
        Card card = paymentRequest.getCard();
        YearMonth expiryDate = YearMonth.of(card.getExpiryYear(), card.getExpiryMonth());

        acquirerRequest.setAmount(purchase.getAmount());
        acquirerRequest.setCardholderName(customer.getBillingAddress().getFirstName());
        acquirerRequest.setCardNumber(card.getCardNumber());

        DemoAcquirer demoAcquirer = new DemoAcquirerImpl();

        DemoAcquirerResponse response = demoAcquirer.send(acquirerRequest);

        return response.getResponseCode();
    }
}
