package com.yemeksiparis;

import org.junit.Test;
import static org.junit.Assert.*;

public class OrderTest {

    @Test
    public void calculateTotal_shouldIncludeItemsAndCoupon() {

        // Arrange
        Customer customer = new Customer("Kerem", "0555 000 00 00", "Istanbul");

        Restaurant restaurant = new Restaurant("Test Restaurant");

        Cart cart = customer.getCart();
        cart.addItem(new MenuItem("Burger", 100.0));

        Coupon coupon = new Coupon("INDIRIM20", 0.20);
        Payment payment = new CashPayment();

        Order order = new Order(customer, restaurant, cart, payment, coupon);

        // Act
        double total = order.calculateTotal();

        // Assert
        assertEquals(80.0, total, 0.0001);
    }
}
