package com.worldpay.paymentSystemV2.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.worldpay.paymentSystemV2.domain.Payment;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class PaymentDao {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Save the payment to the database
     */
    public void createPayment(Payment payment) {
        entityManager.persist(payment);
        entityManager.persist(payment.getCard());
        entityManager.persist(payment.getMerchant());
        entityManager.persist(payment.getShopper());
    }

}
