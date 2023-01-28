package com.example.vetsandpets.helper;

import com.example.vetsandpets.entity.Pet;
import com.example.vetsandpets.entity.User;
import com.example.vetsandpets.model.PetDto;
import com.example.vetsandpets.model.UserDto;
import com.example.vetsandpets.model.UserResponse;

import java.time.LocalDate;

public class Mapper {

    public static Pet fromPetDtoToPet(PetDto petDto) {
        return Pet.builder()
                .id(petDto.getId())
                .lastVisitDate(petDto.getLastVisitDate())
                .name(petDto.getName())
                .type(petDto.getType())
                .build();
    }

    public static PetDto fromPetToPetDto(Pet pet) {
        return PetDto.builder()
                .id(pet.getId())
                .lastVisitDate(LocalDate.now())
                .name(pet.getName())
                .type(pet.getType())
                .build();
    }

    public static UserDto fromUserToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .username(user.getUsername())
                .pets(user.getPets())
                .build();
    }

    public static User fromUserDtoToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .fullName(userDto.getFullName())
                .username(userDto.getUsername())
                .pets(userDto.getPets())
                .build();
    }

    public static UserResponse fromUserToUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .username(user.getUsername())
                .pets(user.getPets())
                .build();
    }
}
