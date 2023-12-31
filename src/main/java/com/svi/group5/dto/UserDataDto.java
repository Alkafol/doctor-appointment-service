package com.svi.group5.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.svi.group5.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserDataDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    @JsonFormat(pattern = "ddMMyyyy")
    private LocalDate dateOfBirth;
    private Role role;
}
