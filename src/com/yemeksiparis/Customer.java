package com.yemeksiparis;

public class Customer extends User {

    private final Cart cart;

    public Customer(String name, String phone, String address,String username,String password) {
        super(name, phone, address, username, password); // User constructor
        this.cart = new Cart();
    }

  
  
 

    public Cart getCart() {
        return cart;
    }
}


