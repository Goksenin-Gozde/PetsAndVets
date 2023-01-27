package com.example.vetsandpets.helper;

import com.example.vetsandpets.entity.Pet;
import com.example.vetsandpets.entity.User;
import com.example.vetsandpets.model.PetDto;
import com.example.vetsandpets.model.UserDto;

import java.time.LocalDate;

public class Mapper {

    public static Pet fromPetDtoToPet(PetDto petDto) {
        return Pet.builder()
                .lastVisitDate(petDto.getLastVisitDate())
                .name(petDto.getName())
                .type(petDto.getType())
                .build();
    }

    public static PetDto fromPetToPetDto(Pet pet) {
        return PetDto.builder()
                .lastVisitDate(LocalDate.now())
                .name(pet.getName())
                .type(pet.getType())
                .build();
    }

    public static UserDto fromUserToUserDto(User user) {
        return UserDto.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .username(user.getUserName())
                .pets(user.getPets())
                .build();
    }

    public static User fromUserDtoToUser(UserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .userName(userDto.getUsername())
                .pets(userDto.getPets())
                .build();
    }
}
