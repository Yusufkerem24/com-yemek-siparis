package com.yemeksiparis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    private final List<MenuItem> items = new ArrayList<>();

    public void addItem(MenuItem item) {
        if (item == null) throw new IllegalArgumentException("MenuItem cannot be null");
        items.add(item);
    }

    public void removeItem(MenuItem item) {
        items.remove(item);
    }

    public List<MenuItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }

    public double total() {
        return items.stream().mapToDouble(MenuItem::getPrice).sum();
    }

    public void printSummary() {
        System.out.println("== Sepet Özeti ==");
        if (items.isEmpty()) {
            System.out.println("Sepet bos.");
            return;
        }
        for (MenuItem item : items) System.out.println("- " + item);
        System.out.println("Toplam: " + total() + " TL");
    }
}

