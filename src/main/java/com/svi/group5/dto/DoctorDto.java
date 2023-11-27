package com.svi.group5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class DoctorDto {
    private UserDataDto userInfo;
    private PositionDto position;
}
