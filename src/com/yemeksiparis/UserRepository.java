package com.yemeksiparis;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static final String FILE_NAME = "users.txt";

    public List<User> loadUsers() {
        List<User> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                users.add(new User(
                        p[0], p[1], p[2], p[3], p[4]
                ));
            }
        } catch (IOException e) {
            // dosya yoksa sorun değil
        }

        return users;
    }

    public void saveUser(User user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(
                user.getUsername() + "," +
                user.getPassword() + "," +
                user.getName() + "," +
                user.getPhone() + "," +
                user.getAddress()
            );
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

