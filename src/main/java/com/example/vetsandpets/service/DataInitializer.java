package com.example.vetsandpets.service;

import com.example.vetsandpets.entity.Role;
import com.example.vetsandpets.entity.User;
import com.example.vetsandpets.exception.UserNotFoundException;
import com.example.vetsandpets.model.RoleType;
import com.example.vetsandpets.model.UserDto;
import com.example.vetsandpets.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class DataInitializer implements CommandLineRunner {


    private UserService userService;


    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // We can use Liquibase too. If we had complicated tasks or in need of changeLogs etc. Liquibase is a better option
    // But I don't think that we need to over-engineer this project
    @Override
    public void run(String... args) {
        // Create default roles
        Optional<Role> roleOpt = roleService.createIfNotFound(RoleType.ADMIN);
        Optional<Role> roleOptional = roleService.createIfNotFound(RoleType.USER);

        Set<Role> adminRole = Set.of(roleService.findByName(RoleType.ADMIN));
        try{
        userService.findByUserName("admin");
        }catch (UserNotFoundException e){
            // Create admin user
            UserDto userDto = UserDto.builder()
                    .username("admin")
                    .password("password")
                    .fullName("admin admin")
                    .roles(adminRole)
                    .build();
            userService.registerUser(userDto);
        }
    }
}
