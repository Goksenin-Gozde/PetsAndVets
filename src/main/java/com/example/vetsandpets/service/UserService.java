package com.example.vetsandpets.service;

import com.example.vetsandpets.entity.Pet;
import com.example.vetsandpets.entity.User;
import com.example.vetsandpets.exception.UserNotFoundException;
import com.example.vetsandpets.helper.Mapper;
import com.example.vetsandpets.model.PetDto;
import com.example.vetsandpets.model.UserDto;
import com.example.vetsandpets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Autowiring constructor instead of field is more reliable, secure and performance friendly
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDto> findAll(){
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        users.forEach((user) -> userDtos.add(Mapper.fromUserToUserDto(user)));
        return userDtos;
    }

    public User registerUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setUserName(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }


    public UserDto findByUserName(String userName) {
        User existingUser = userRepository.findByUserName(userName).orElseThrow(
                () -> new UserNotFoundException("User Not Found")
        );
        return Mapper.fromUserToUserDto(existingUser);
    }


}
