package com.example.vetsandpets.model;

import com.example.vetsandpets.entity.Pet;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserDto {
    private String name;
    private String surname;
    private String username;
    private String password;
    private List<Pet> pets;
}
