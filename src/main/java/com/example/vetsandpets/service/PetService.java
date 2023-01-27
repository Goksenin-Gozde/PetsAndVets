package com.example.vetsandpets.service;

import com.example.vetsandpets.entity.Pet;
import com.example.vetsandpets.entity.User;
import com.example.vetsandpets.exception.UserNotFoundException;
import com.example.vetsandpets.helper.Mapper;
import com.example.vetsandpets.model.PetDto;
import com.example.vetsandpets.model.UserDto;
import com.example.vetsandpets.repository.PetRepository;
import com.example.vetsandpets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PetService {

    private final UserRepository userRepository;
    private final PetRepository petRepository;

    // Autowiring constructor instead of field is more reliable, secure and performance friendly
    @Autowired
    public PetService(UserRepository userRepository, PetRepository petRepository) {
        this.userRepository = userRepository;
        this.petRepository = petRepository;
    }

    public Pet addPet(Long userId, PetDto petDto) {
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User Not Found")
        );
        petDto.setLastVisitDate(LocalDate.now());
        Pet pet = Mapper.fromPetDtoToPet(petDto);
        pet.setUser(existingUser);

        return petRepository.save(pet);
    }
}
