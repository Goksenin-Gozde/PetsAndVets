package com.example.vetsandpets.repository;

import com.example.vetsandpets.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
