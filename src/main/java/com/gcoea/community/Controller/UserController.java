package com.gcoea.community.Controller;

import com.gcoea.community.Entity.Users;
import com.gcoea.community.Enums.Role;
import com.gcoea.community.Repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UsersRepo usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Users user) {
        try {
            logger.info("Attempting to register user: {}", user.getUsername());
            if (usersRepository.findByUsername(user.getUsername()).isPresent()) {
                logger.warn("Username already exists: {}", user.getUsername());
                return ResponseEntity.badRequest().body("Username already exists!");
            }
            if (usersRepository.findByEmail(user.getEmail()).isPresent()) {
                logger.warn("Email already exists: {}", user.getEmail());
                return ResponseEntity.badRequest().body("Email already exists!");
            }

            // Set default values if not provided (though frontend should send all)
            if (user.getName() == null) user.setName("New User");
            if (user.getRole() == null) user.setRole(Role.STUDENT);

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            Users savedUser = usersRepository.save(user);
            logger.info("User saved successfully: {}", savedUser.getId());
            return ResponseEntity.ok("User registered successfully!");
        } catch (Exception e) {
            logger.error("Error registering user: {}", e.getMessage(), e);
            return ResponseEntity.status(500).body("Database connection failed or internal error: " + e.getMessage());
        }
    }
}