package com.example.vetsandpets.model;

import com.example.vetsandpets.entity.Pet;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserResponse {
    private Long id;
    private String fullName;
    private String username;
    private List<Pet> pets;
}
