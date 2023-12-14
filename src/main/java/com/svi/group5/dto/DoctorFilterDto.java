package com.svi.group5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorFilterDto {
    private Long positionId;
    private Integer experienceLowerBound;
}
