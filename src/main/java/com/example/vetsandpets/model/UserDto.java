package com.example.vetsandpets.model;

import com.example.vetsandpets.entity.Pet;
import com.example.vetsandpets.entity.Role;
import lombok.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserDto {
    private Long id;
    private String fullName;
    private String username;
    private String password;
    private List<Pet> pets;
    private Set<Role> roles;
}
