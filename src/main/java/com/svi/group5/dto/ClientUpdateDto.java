package com.svi.group5.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientUpdateDto {
    @JsonProperty("id")
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    @JsonFormat(pattern = "ddMMyyyy")
    private LocalDate dateOfBirth;
}
