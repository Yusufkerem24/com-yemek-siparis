package com.yemeksiparis;

import org.junit.Test;
import static org.junit.Assert.*;

public class CouponTest {

    @Test
    public void applyDiscount_shouldReduceTotal() {
        Coupon coupon = new Coupon("INDIRIM10", 0.10);

        double discounted = coupon.applyDiscount(200.0);

        assertEquals(180.0, discounted, 0.0001);
    }

    @Test
    public void zeroDiscount_shouldNotChangeTotal() {
        Coupon coupon = new Coupon("NO", 0.0);

        double discounted = coupon.applyDiscount(150.0);

        assertEquals(150.0, discounted, 0.0001);
    }
}
