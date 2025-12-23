package com.yemeksiparis;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order implements Orderable {
    private final Customer customer;
    private final Restaurant restaurant;
    private final List<MenuItem> items;
    private final LocalDateTime createdAt;

    public Order(Customer customer, Restaurant restaurant, List<MenuItem> items) {
        if (customer == null || restaurant == null) throw new IllegalArgumentException("Customer/Restaurant null olamaz");
        if (items == null || items.isEmpty()) throw new IllegalArgumentException("Sipariş items boş olamaz");

        this.customer = customer;
        this.restaurant = restaurant;
        this.items = new ArrayList<>(items); // encapsulation: dış listeyi kopyaladık
        this.createdAt = LocalDateTime.now();
    }

    Order(Customer customer, Restaurant restaurant, Cart cart, Payment payment, Coupon coupon) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public double calculateTotal() {
        return items.stream().mapToDouble(MenuItem::getPrice).sum();
    }

    @Override
    public void placeOrder() {
        System.out.println("Sipariş oluşturuldu: " + restaurant.getName());
        System.out.println("Müşteri: " + customer.getName() + " | Adres: " + customer.getAddress());
        System.out.println("Tarih: " + createdAt);
        System.out.println("Ürünler:");
        for (MenuItem item : items) System.out.println("- " + item);
        System.out.println("Toplam: " + calculateTotal() + " TL");
    }
}

