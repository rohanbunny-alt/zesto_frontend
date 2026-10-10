package com.zesto.app.controller;

import com.zesto.app.model.AuthDTOs.*;
import com.zesto.app.model.User;
import com.zesto.app.security.JwtUtil;
import com.zesto.app.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final DataService dataService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(DataService dataService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.dataService = dataService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody SignupRequest req) {
        if (req.getEmail() == null || req.getEmail().trim().isEmpty() || req.getPassword() == null || req.getPassword().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new AuthResponse(false, null, "Email and password are required", null));
        }

        Optional<User> existing = dataService.findUserByEmail(req.getEmail());
        if (existing.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new AuthResponse(false, null, "Email address is already registered", null));
        }

        String role = (req.getRole() != null && req.getRole().equalsIgnoreCase("ADMIN")) ? "ADMIN" : "CUSTOMER";
        String userId = "usr-" + UUID.randomUUID().toString().substring(0, 8);

        User newUser = new User(
                userId,
                req.getName() != null ? req.getName() : "Foodie User",
                req.getEmail().toLowerCase().trim(),
                passwordEncoder.encode(req.getPassword()),
                req.getPhone() != null ? req.getPhone() : "+91 98765 43210",
                role
        );

        dataService.saveUser(newUser);

        String token = jwtUtil.generateToken(newUser.getEmail(), newUser.getRole());

        // Hide password in response
        User safeUser = new User(newUser.getId(), newUser.getName(), newUser.getEmail(), null, newUser.getPhone(), newUser.getRole());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthResponse(true, token, "Account created successfully!", safeUser));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest req) {
        if (req.getEmail() == null || req.getPassword() == null) {
            return ResponseEntity.badRequest().body(new AuthResponse(false, null, "Email and password are required", null));
        }

        Optional<User> userOpt = dataService.findUserByEmail(req.getEmail().toLowerCase().trim());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse(false, null, "Invalid email or password", null));
        }

        User user = userOpt.get();
        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse(false, null, "Invalid email or password", null));
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

        User safeUser = new User(user.getId(), user.getName(), user.getEmail(), null, user.getPhone(), user.getRole());

        return ResponseEntity.ok(new AuthResponse(true, token, "Welcome back, " + user.getName() + "!", safeUser));
    }

    @GetMapping("/me")
    public ResponseEntity<AuthResponse> getCurrentUser(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse(false, null, "No token provided", null));
        }

        String token = authHeader.substring(7);
        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse(false, null, "Invalid or expired token", null));
        }

        String email = jwtUtil.getEmailFromToken(token);
        Optional<User> userOpt = dataService.findUserByEmail(email);

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AuthResponse(false, null, "User not found", null));
        }

        User user = userOpt.get();
        User safeUser = new User(user.getId(), user.getName(), user.getEmail(), null, user.getPhone(), user.getRole());

        return ResponseEntity.ok(new AuthResponse(true, token, "Authenticated", safeUser));
    }
}
