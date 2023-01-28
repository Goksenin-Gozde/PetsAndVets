package com.example.vetsandpets.service;

import com.example.vetsandpets.entity.Role;
import com.example.vetsandpets.entity.User;
import com.example.vetsandpets.exception.RoleNotFoundException;
import com.example.vetsandpets.exception.UserNotFoundException;
import com.example.vetsandpets.helper.Mapper;
import com.example.vetsandpets.model.RoleType;
import com.example.vetsandpets.model.UserDto;
import com.example.vetsandpets.model.UserResponse;
import com.example.vetsandpets.repository.RoleRepository;
import com.example.vetsandpets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    // Autowiring constructor instead of field is more reliable, secure and performance friendly
    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public List<UserResponse> findAll(){
        List<User> users = userRepository.findAll();
        List<UserResponse> userDtos = new ArrayList<>();
        users.forEach((user) -> userDtos.add(Mapper.fromUserToUserResponse(user)));
        return userDtos;
    }

    public User registerUser(UserDto userDto) {
        Role userRole = roleRepository.findByName(RoleType.USER).orElseThrow(
                () -> new RoleNotFoundException("Role Not Found")
        );
        User user = new User();
        user.setFullName(userDto.getFullName());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(Set.of(userRole));
        return userRepository.save(user);
    }


    public UserResponse findByUserName(String userName) {
        User existingUser = userRepository.findByUsername(userName).orElseThrow(
                () -> new UserNotFoundException("User Not Found")
        );
        return Mapper.fromUserToUserResponse(existingUser);
    }

    public UserResponse updateUser(Long userId, UserDto userDto){
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User Not Found")
        );

        if(Objects.nonNull(userDto.getFullName())){
            existingUser.setFullName(userDto.getFullName());
        }

        if(Objects.nonNull(userDto.getPets())){
            existingUser.setPets(userDto.getPets());
        }

        return Mapper.fromUserToUserResponse(userRepository.save(existingUser));
    }

    public void assignRole(String username, RoleType roleName) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found"));
        Role role = roleRepository.findByName(roleName).orElseThrow(() -> new RoleNotFoundException("Role not found"));
        user.getRoles().add(role);
        userRepository.save(user);
    }

    public void revokeRole(String username, RoleType roleName) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found"));
        Role role = roleRepository.findByName(roleName).orElseThrow(() -> new RoleNotFoundException("Role not found"));
        user.getRoles().remove(role);
        userRepository.save(user);
    }


}
