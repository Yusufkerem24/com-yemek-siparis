package com.yemeksiparis;

public class Customer extends User {

    private final Cart cart;

    public Customer(String name, String phone, String address) {
        super(name, phone, address); // User constructor
        this.cart = new Cart();
    }

  
 

    public Cart getCart() {
        return cart;
    }
}


