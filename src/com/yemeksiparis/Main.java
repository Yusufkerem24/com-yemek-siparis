package com.yemeksiparis;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserService userService = new UserService();

        System.out.println("=== Online Yemek Sipariş Sistemi ===");
        System.out.println("1) Giriş Yap");
        System.out.println("2) Kayıt Ol");
        System.out.print("Seçim: ");

        int authChoice;
        try {
            authChoice = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Hatalı giriş!");
            sc.close();
            return;
        }

        System.out.print("Kullanıcı adı: ");
        String username = sc.nextLine();

        System.out.print("Şifre: ");
        String password = sc.nextLine();

        String name, phone, address;

        if (authChoice == 1) {
            // LOGIN
            boolean success = userService.login(username, password);
            if (!success) {
                System.out.println("❌ Giriş başarısız!");
                sc.close();
                return;
            }
            System.out.println("✅ Giriş başarılı!");

            // Basit demo için kullanıcı bilgilerini tekrar alıyoruz
            System.out.print("Ad Soyad: ");
            name = sc.nextLine();
            System.out.print("Telefon: ");
            phone = sc.nextLine();
            System.out.print("Adres: ");
            address = sc.nextLine();

        } else if (authChoice == 2) {
            // REGISTER
            System.out.print("Ad Soyad: ");
            name = sc.nextLine();
            System.out.print("Telefon: ");
            phone = sc.nextLine();
            System.out.print("Adres: ");
            address = sc.nextLine();

            User newUser = new User(username, password, name, phone, address);
            boolean registered = userService.register(newUser);

            if (!registered) {
                System.out.println("❌ Kullanıcı zaten mevcut!");
                sc.close();
                return;
            }
            System.out.println("✅ Kayıt başarılı!");

        } else {
            System.out.println("Hatalı seçim!");
            sc.close();
            return;
        }

        // ----------------------------
        // BURADAN SONRASI SENİN KODUN
        // ----------------------------

        Customer customer = new Customer(name, phone, address, username, password);

        // 1) Restaurant + Menu
        Restaurant r = new Restaurant("Burger House");
        r.addMenuItem(new MenuItem("Burger", 90));
        r.addMenuItem(new MenuItem("Patates", 35));
        r.addMenuItem(new MenuItem("Kola", 25));

        // 2) Menüden ürün seçimi
        while (true) {
            System.out.println();
            r.printMenu();
            System.out.println("0) Siparişi tamamla");
            System.out.print("Sepete eklemek istediğiniz ürün numarası: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Hatalı giriş!");
                continue;
            }

            if (choice == 0) break;

            if (choice < 1 || choice > r.getMenu().size()) {
                System.out.println("Hatalı seçim!");
                continue;
            }

            MenuItem selected = r.getMenu().get(choice - 1);
            customer.getCart().addItem(selected);
            System.out.println(selected.getName() + " sepete eklendi.");
        }

        if (customer.getCart().isEmpty()) {
            System.out.println("Sepet boş.");
            sc.close();
            return;
        }

        customer.getCart().printSummary();

        Order order = new Order(customer, r, customer.getCart().getItems());

        System.out.print("Kupon kodu (yoksa ENTER): ");
        String couponCode = sc.nextLine().trim();

        double totalToPay = order.calculateTotal();

        if (couponCode.equalsIgnoreCase("INDIRIM10")) {
            Coupon coupon = new Coupon("INDIRIM10", 0.10);
            totalToPay = coupon.applyDiscount(totalToPay);
            System.out.println("İndirim uygulandı!");
        }

        System.out.println("Ödeme yöntemi:");
        System.out.println("1) Kredi Kartı");
        System.out.println("2) Nakit");
        int payChoice = Integer.parseInt(sc.nextLine());

        Payment payment = (payChoice == 2)
                ? new CashPayment()
                : new CreditCardPayment();

        order.placeOrder();
        payment.pay(totalToPay);

        System.out.print("Restoran puanı (1-5): ");
        int rating = Integer.parseInt(sc.nextLine());
        r.addRating(rating);

        System.out.println("Teşekkürler! Ortalama: " + r.getAverageRating());

        sc.close();
    }
}

