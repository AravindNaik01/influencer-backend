package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository repo;

    // REGISTER
    public String register(User user) {

        if (repo.findByUsername(user.getUsername()) != null) {
            return "Username already exists";
        }

        repo.save(user);
        return "User registered";
    }

    // LOGIN
    public String login(User user) {

        User existing = repo.findByUsername(user.getUsername());

        if (existing != null && existing.getPassword().equals(user.getPassword())) {
            return JwtUtil.generateToken(user.getUsername());
        }

        return "Invalid credentials";
    }
}