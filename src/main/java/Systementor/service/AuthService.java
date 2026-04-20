package Systementor.service;

import Systementor.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;

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

        String hashedPassword = BCrypt.hashpw(password,BCrypt.gensalt());

        return userRepository.saveUser(username,hashedPassword, "USER");

    }


}
