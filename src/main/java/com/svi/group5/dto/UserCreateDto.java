package com.svi.group5.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.svi.group5.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserCreateDto {
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String password;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateOfBirth;
    private Role role;
}
