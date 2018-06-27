package com.worldpay.paymentSystemV2.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.worldpay.paymentSystemV2.domain.Card;
import com.worldpay.paymentSystemV2.domain.Merchant;
import com.worldpay.paymentSystemV2.domain.Payment;
import com.worldpay.paymentSystemV2.domain.Shopper;
import com.worldpay.paymentSystemV2.domain.Visa;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class PaymentDao {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Save the payment to the database
     */
    public void create(Payment payment, Visa card, Merchant merchant, Shopper shopper) {
        entityManager.persist(payment);
        entityManager.persist(card);
        entityManager.persist(merchant);
        entityManager.persist(shopper);
    }

}
