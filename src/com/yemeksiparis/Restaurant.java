package com.yemeksiparis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Restaurant {
    private String name;
    private final List<MenuItem> menu = new ArrayList<>();

    // Restoran puanlama
    private int totalScore = 0;
    private int ratingCount = 0;

    public Restaurant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addMenuItem(MenuItem item) {
        if (item == null) {
            throw new IllegalArgumentException("MenuItem cannot be null");
        }
        menu.add(item);
    }

    public List<MenuItem> getMenu() {
        return Collections.unmodifiableList(menu);
    }

    public void printMenu() {
        System.out.println("=== " + name + " Menü ===");
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + ") " + menu.get(i));
        }
    }

    // --- Puanlama sistemi ---
    public void addRating(int score) {
        if (score < 1 || score > 5) {
            System.out.println("Puan 1-5 arası olmalı. Puan eklenmedi.");
            return;
        }
        totalScore += score;
        ratingCount++;
    }

    public double getAverageRating() {
        if (ratingCount == 0) return 0.0;
        return (double) totalScore / ratingCount;
    }

    public int getRatingCount() {
        return ratingCount;
    }
}
