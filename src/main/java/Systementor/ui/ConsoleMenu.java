package Systementor.ui;

import Systementor.model.User;
import Systementor.service.AuthService;

import java.util.Scanner;

public class ConsoleMenu {


    private final Scanner scanner = new Scanner(System.in);
    private final AuthService service = new AuthService();

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("\n--- Secure note----");
            System.out.println("1. Register new user");
            System.out.println("2. Login user");
            System.out.println("3. Exit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> register();
                case "2" -> login();
                case "3" -> running = false;
                default -> System.out.println("Invalid choice");

            }
        }
    }

    private void register() {
        System.out.println("Enter username");
        String username = scanner.nextLine().trim();

        System.out.println("Enter password");
        String password = scanner.nextLine();


        boolean success = service.register(username,password);
        if (success) {
            System.out.println("User registered successfully");
        } else {
            System.out.println("Could not save user");
        }

    }

    private void login() {
        System.out.println("Enter username");
        String username = scanner.nextLine().trim();

        System.out.println("Enter password");
        String password = scanner.nextLine().trim();

        User user = service.login(username, password);

        if (user != null) {
            System.out.println(user.getPassword());
            System.out.println(user.getRole());
            System.out.println(user.getId());
            System.out.println(user.getUsername() + " successfully logged in");
        } else {
            System.out.println("Login failed");
        }
    }

}
