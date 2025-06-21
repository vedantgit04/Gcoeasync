package com.gcoea.community;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHasher {

    public static void main(String[] args) {
        // Create a BCryptPasswordEncoder instance
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Passwords to hash
        String[] passwords = {"student123", "admin123", "society123"};

        // Hash and print each password
        for (String password : passwords) {
            String hashedPassword = passwordEncoder.encode(password);
            System.out.println("Password: " + password + " -> Hashed: " + hashedPassword);
        }
    }
}