package com.svi.group5.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DoctorDataDto {
    @JsonUnwrapped
    private UserDataDto userInfo;
    @JsonUnwrapped
    private PositionDataDto position;
    @JsonUnwrapped
    private String description;
    @JsonUnwrapped
    private Integer experience;
}
