package com.yemeksiparis;

import org.junit.Test;
import static org.junit.Assert.*;

public class CartTest {

    @Test
    public void total_shouldReturnSumOfItems() {
        Cart cart = new Cart();
        cart.addItem(new MenuItem("Burger", 90.0));
        cart.addItem(new MenuItem("Kola", 25.0));

        assertEquals(115.0, cart.total(), 0.0001);
    }

    @Test
    public void removeItem_shouldDecreaseTotal() {
        Cart cart = new Cart();
        MenuItem burger = new MenuItem("Burger", 90.0);
        MenuItem kola = new MenuItem("Kola", 25.0);

        cart.addItem(burger);
        cart.addItem(kola);
        cart.removeItem(kola);

        assertEquals(90.0, cart.total(), 0.0001);
    }

    @Test
    public void emptyCart_totalShouldBeZero() {
        Cart cart = new Cart();
        assertEquals(0.0, cart.total(), 0.0001);
    }
}



