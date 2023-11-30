package com.svi.group5.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.svi.group5.enums.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AppointmentDataDto {
    private Long id;
    private UserDataDto clientInfo;
    private UserDataDto doctorInfo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endTime;
    private AppointmentStatus status;
}
