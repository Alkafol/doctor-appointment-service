package com.svi.group5.dto;

import com.svi.group5.enums.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppointmentUpdateDto {
    private Long id;
    private Long clientId;
    private Long doctorId;
    private AppointmentStatus status;
}
