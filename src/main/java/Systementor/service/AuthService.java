package Systementor.service;

import Systementor.model.User;
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

        if(userRepository.existsByUsername(username)) {
            System.out.println("Username already exists");
            return false;
        }

        String hashedPassword = BCrypt.hashpw(password,BCrypt.gensalt());
        return userRepository.saveUser(username,hashedPassword, "USER");

    }


    public User login(String username, String password) {
        if(username == null || username.isBlank()){
            System.out.println("Username is null or empty");
            return null;
        }

        User user = userRepository.findByUsername(username);

        if(user == null) {
            System.out.println("User not found");
            return null;
        }

        boolean match = BCrypt.checkpw(password, user.getPassword());
        if (match){
            return user;
        }
        System.out.println("Wrong username or password");
        return null;
    }


}
