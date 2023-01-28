package com.example.vetsandpets.repository;

import com.example.vetsandpets.entity.Role;
import com.example.vetsandpets.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleType roleName);
    boolean existsByName(RoleType roleName);
}
