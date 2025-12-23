package com.yemeksiparis;

public class Coupon {
    private String code;
    private double discountRate; // 0.10 = %10

    public Coupon(String code, double discountRate) {
        this.code = code;
        this.discountRate = discountRate;
    }

    public String getCode() {
        return code;
    }

    public double applyDiscount(double total) {
        return total - (total * discountRate);
    }
}

