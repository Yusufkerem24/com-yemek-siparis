package com.yemeksiparis;

import org.junit.Test;
import static org.junit.Assert.*;

public class PaymentTest {

    @Test
    public void cashPayment_shouldExecuteWithoutError() {
        Payment payment = new CashPayment();
        payment.pay(100.0); // exception atmıyorsa test geçer
        assertTrue(true);
    }

    @Test
    public void creditCardPayment_shouldExecuteWithoutError() {
        Payment payment = new CreditCardPayment();
        payment.pay(250.0);
        assertTrue(true);
    }
}
