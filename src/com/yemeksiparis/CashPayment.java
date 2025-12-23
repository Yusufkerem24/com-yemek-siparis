package com.yemeksiparis;

public class CashPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println(amount + " TL kapıda nakit ödendi.");
    }

    @Override
    public String methodName() {
        return "Cash";
    }
}
