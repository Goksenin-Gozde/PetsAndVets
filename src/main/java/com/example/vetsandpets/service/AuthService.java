package com.example.vetsandpets.service;

import com.example.vetsandpets.entity.User;
import com.example.vetsandpets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean login(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            return false;
        }

        User user = userOptional.get();

        return passwordEncoder.matches(password, user.getPassword());
    }
}
