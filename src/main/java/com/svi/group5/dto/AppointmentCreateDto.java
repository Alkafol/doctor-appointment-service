package com.svi.group5.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentCreateDto {
    private Long doctorId;
    @JsonFormat(pattern = "HH:mm dd-MM-yyyy")
    private LocalDateTime startTime;
    @JsonFormat(pattern = "HH:mm dd-MM-yyyy")
    private LocalDateTime endTime;
}