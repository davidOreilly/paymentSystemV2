package com.worldpay.paymentSystemV2.controllers;

import com.worldpay.paymentSystemV2.dao.PaymentDao;
import com.worldpay.paymentSystemV2.model.PaymentServiceRequest;
import com.worldpay.paymentSystemV2.service.RequestFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RequestControllerTest {

    @Mock private PaymentServiceRequest paymentServiceRequest;
    @Mock private PaymentDao paymentDao;
    @Mock private RequestFactory requestFactory;

    private RequestController requestController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        requestController = new RequestController(paymentDao, requestFactory);
    }

    @Test
    public void unrecognisedOperationReturnsNull() throws Exception {
        when(paymentServiceRequest.getOperation()).thenReturn("SOMEOPERATION");

        String returnValue = requestController.submitPaymentServiceRequest(paymentServiceRequest);

        Assert.assertNull(returnValue);
    }

    @Test
    public void paymentOperationCallsRequestFactoryAndPaymentDao() throws Exception {
        when(paymentServiceRequest.getOperation()).thenReturn("PAYMENT");

        requestController.submitPaymentServiceRequest(paymentServiceRequest);

        verify(paymentDao).createPayment(any());
        verify(requestFactory).createPaymentRequest(paymentServiceRequest);
    }

    @Test
    public void refundOperationCallsRequestFactory() throws Exception {
        when(paymentServiceRequest.getOperation()).thenReturn("REFUND");

        requestController.submitPaymentServiceRequest(paymentServiceRequest);

        verify(requestFactory).createRefundRequest();
    }
}
