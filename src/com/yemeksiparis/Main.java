package com.yemeksiparis;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1) Restaurant + Menu (Menü görüntüleme)
        Restaurant r = new Restaurant("Burger House");
        r.addMenuItem(new MenuItem("Burger", 90));
        r.addMenuItem(new MenuItem("Patates", 35));
        r.addMenuItem(new MenuItem("Kola", 25));

        // 2) Customer bilgilerini ekrandan al
        System.out.println("=== Online Yemek Sipariş Sistemi ===");
        System.out.print("Ad Soyad: ");
        String name = sc.nextLine();

        System.out.print("Telefon: ");
        String phone = sc.nextLine();

        System.out.print("Adres: ");
        String address = sc.nextLine();

        Customer customer = new Customer(name, phone, address);

        // 3) Menüden ürün seçip sepete ekleme
        while (true) {
            System.out.println();
            r.printMenu();
            System.out.println("0) Siparişi tamamla");
            System.out.print("Sepete eklemek istediğiniz ürün numarası: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Hatalı giriş! Lütfen sayı gir.");
                continue;
            }

            if (choice == 0) break;

            if (choice < 1 || choice > r.getMenu().size()) {
                System.out.println("Hatalı seçim! Tekrar dene.");
                continue;
            }

            MenuItem selected = r.getMenu().get(choice - 1);
            customer.getCart().addItem(selected);
            System.out.println(selected.getName() + " sepete eklendi.");
        }

        // Sepet boşsa çık
        if (customer.getCart().isEmpty()) {
            System.out.println("\nSepet boş. Sipariş oluşturulmadı.");
            sc.close();
            return;
        }

        // 4) Sipariş özeti
        System.out.println();
        customer.getCart().printSummary();

        // 5) Sipariş oluşturma
        Order order = new Order(customer, r, customer.getCart().getItems());

        // 6) Kupon/İndirim sistemi
        System.out.print("\nKupon kodu var mı? (yoksa ENTER): ");
        String couponCode = sc.nextLine().trim();

        double totalToPay = order.calculateTotal();

        if (!couponCode.isEmpty()) {
            if (couponCode.equalsIgnoreCase("INDIRIM10")) {
                Coupon coupon = new Coupon("INDIRIM10", 0.10);
                totalToPay = coupon.applyDiscount(totalToPay);
                System.out.println("Kupon uygulandı! Yeni toplam: " + totalToPay + " TL");
            } else {
                System.out.println("Kupon geçersiz. İndirim uygulanmadı.");
            }
        }

        // 7) Ödeme yöntemi (Polymorphism)
        System.out.println("\nÖdeme yöntemi seç:");
        System.out.println("1) Kredi Kartı");
        System.out.println("2) Nakit");
        System.out.print("Seçim: ");

        int payChoice;
        try {
            payChoice = Integer.parseInt(sc.nextLine().trim());
        } catch (Exception e) {
            payChoice = 1; // default
        }

        Payment payment = (payChoice == 2) ? new CashPayment() : new CreditCardPayment();

        // 8) Siparişi sisteme “geçir” ve göster
        System.out.println("\n=== Sipariş Detayı ===");
        order.placeOrder();

        System.out.println("Ödeme yöntemi: " + payment.methodName());
        payment.pay(totalToPay);

        // 9) Restoran puanlama
        System.out.print("\nRestoranı puanla (1-5): ");
        int rating;
        try {
            rating = Integer.parseInt(sc.nextLine().trim());
        } catch (Exception e) {
            rating = 5;
        }

        r.addRating(rating);
        System.out.println("Teşekkürler! Güncel restoran ortalaması: " + r.getAverageRating());

        sc.close();
    }
}

