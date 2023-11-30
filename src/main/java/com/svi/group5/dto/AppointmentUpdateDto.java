package com.svi.group5.dto;

import com.svi.group5.enums.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class  AppointmentUpdateDto {
    private Long id;
    private Long clientId;
    private AppointmentStatus status;
}
