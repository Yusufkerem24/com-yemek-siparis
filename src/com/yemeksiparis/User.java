package com.yemeksiparis;

public class User {

    private String username;
    private String password;

    private String name;
    private String phone;
    private String address;

    public User(String username, String password, String name, String phone, String address) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
}


