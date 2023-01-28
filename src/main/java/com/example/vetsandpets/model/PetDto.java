package com.example.vetsandpets.model;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PetDto {
    private Long id;
    private String name;
    private PetType type;
    private LocalDate lastVisitDate;
}
