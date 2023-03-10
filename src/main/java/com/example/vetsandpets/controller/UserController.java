package com.example.vetsandpets.controller;


import com.example.vetsandpets.entity.Pet;
import com.example.vetsandpets.entity.User;
import com.example.vetsandpets.exception.AuthorizationError;
import com.example.vetsandpets.model.PetDto;
import com.example.vetsandpets.model.UserDto;
import com.example.vetsandpets.model.UserResponse;
import com.example.vetsandpets.service.PetService;
import com.example.vetsandpets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final PetService petService;

    @Autowired
    public UserController(UserService userService, PetService petService) {

        this.userService = userService;
        this.petService = petService;
    }

    @GetMapping
    public ResponseEntity findAll(Authentication authentication) {
        try {
            List<UserResponse> users = userService.findAll(authentication);
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody UserDto userDto) {
        try {
            User user = userService.registerUser(userDto);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody UserDto userDto, Authentication authentication) {
        try {
            UserResponse user = userService.updateUser(id, userDto, authentication);
            return new ResponseEntity(user, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/pets")
    public ResponseEntity addPet(@PathVariable Long id, @RequestBody PetDto pet) {

        try {
            Pet savedPet = petService.addPet(id, pet);
            return new ResponseEntity(savedPet, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}

