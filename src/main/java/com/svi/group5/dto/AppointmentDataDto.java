package com.svi.group5.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.svi.group5.enums.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AppointmentDataDto {
    private Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserDataDto clientInfo;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserDataDto doctorInfo;
    @JsonFormat(pattern = "HH:mm dd-MM-yyyy")
    private LocalDateTime startTime;
    @JsonFormat(pattern = "HH:mm dd-MM-yyyy")
    private LocalDateTime endTime;
    private AppointmentStatus status;
}
