package com.example.vetsandpets.service;

import com.example.vetsandpets.helper.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final CustomUserDetailService customUserDetailService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthService(CustomUserDetailService customUserDetailService, AuthenticationManager authenticationManager, JwtUtil jwtUtil){
        this.customUserDetailService = customUserDetailService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public String login(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException ex) {
            throw new Exception("Incorrect username or password", ex);
        }
        final UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
        return jwtUtil.generateToken(userDetails);
    }
}
