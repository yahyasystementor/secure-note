package Systementor.service;

import Systementor.repository.UserRepository;

public class AuthService {
    private final UserRepository userRepository = new UserRepository();
    public boolean register(String username, String password) {
        if (username == null || username.isBlank()) {
            System.out.println("Username is null or empty");
            return false;
        }
        if (password == null || password.isBlank()) {
            System.out.println("Password is null or empty");
            return false;
        }
        return userRepository.saveUser(username,password, "USER");

    }


}
