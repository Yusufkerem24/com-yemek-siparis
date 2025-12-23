package com.yemeksiparis;

public class CreditCardPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println(amount + " TL kredi kartı ile ödendi.");
    }

    @Override
    public String methodName() {
        return "Credit Card";
    }
}
