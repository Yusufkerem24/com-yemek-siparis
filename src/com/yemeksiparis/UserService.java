package com.yemeksiparis;

import java.util.List;

public class UserService {

    private final UserRepository repo = new UserRepository();

    public boolean login(String username, String password) {
        List<User> users = repo.loadUsers();

        return users.stream().anyMatch(u ->
                u.getUsername().equals(username) &&
                u.getPassword().equals(password)
        );
    }

    public boolean register(User user) {
        List<User> users = repo.loadUsers();

        boolean exists = users.stream()
                .anyMatch(u -> u.getUsername().equals(user.getUsername()));

        if (exists) return false;

        repo.saveUser(user);
        return true;
    }
}

