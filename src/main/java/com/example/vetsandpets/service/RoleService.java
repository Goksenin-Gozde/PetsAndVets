package com.example.vetsandpets.service;

import com.example.vetsandpets.entity.Role;
import com.example.vetsandpets.exception.RoleNotFoundException;
import com.example.vetsandpets.model.RoleType;
import com.example.vetsandpets.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByName(RoleType roleName){
        return  roleRepository.findByName(roleName)
                .orElseThrow(() -> new RoleNotFoundException("Role Not Found"));
    }

    public Optional<Role> createIfNotFound(RoleType name) {
        if (!roleRepository.existsByName(name)) {
            return saveRoleWithName(name);
        }
        return Optional.empty();
    }

    public Optional<Role> saveRoleWithName(RoleType roleName) {
        Role role = Role.builder()
                .name(roleName)
                .users(Collections.emptySet())
                .build();

        try {
            return Optional.of(roleRepository.save(role));
        }catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
