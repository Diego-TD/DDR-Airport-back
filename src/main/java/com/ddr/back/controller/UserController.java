package com.ddr.back.controller;

import com.ddr.back.entity.User;
import com.ddr.back.repository.UserRepository;
import com.ddr.back.util.userRoles;
import jakarta.annotation.Nonnull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.mindrot.jbcrypt.BCrypt;

@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> list = userRepository.findAll();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long id) {
        try {
            if (!userRepository.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(userRepository.findById(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Nonnull @RequestBody User user) {
        try {
            if (user.getUsername().isEmpty() || user.getPassword().isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data");
            }
            User userFound = userRepository.findByUsername(user.getUsername());
            if (userFound != null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
            }

            user.setCreatedAt(LocalDateTime.now());
            if (user.getRoles() == null){
                List<String> roles = new ArrayList<>();
                roles.add(String.valueOf(userRoles.ROLE_USER));
                user.setRoles(roles);
            }

            String password = user.getPassword();
            String encryptedPW = BCrypt.hashpw(password, BCrypt.gensalt());
            user.setPassword(encryptedPW);

            userRepository.save(user);

            return ResponseEntity.status(HttpStatus.CREATED).body("Created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create");
        }
    }

    //TODO: developed put or patch method to update the user object (like, updating password, or adding more info when buying a flight ticket)

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        User foundUser = userRepository.findByUsername(user.getUsername());

        if (foundUser == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }
        String candidate = user.getPassword();
        String hashed = foundUser.getPassword();

        if (BCrypt.checkpw(candidate, hashed)) {
            System.out.println("It matches");
            Long token = generateToken(foundUser);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            System.out.println("It does not match");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    private Long generateToken(User user) {
        return user.getId();
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        if (id == null) {
            return ResponseEntity.badRequest().body("User ID cannot be null");
        }
        try {
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user");
        }
    }
}
