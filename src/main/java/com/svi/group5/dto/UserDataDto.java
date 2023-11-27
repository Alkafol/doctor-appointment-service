package com.svi.group5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserDataDto {
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private LocalDate dateOfBirth;
}