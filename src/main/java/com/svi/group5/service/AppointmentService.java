package com.svi.group5.service;

import com.svi.group5.entity.Appointment;
import com.svi.group5.entity.User;
import com.svi.group5.enums.AppointmentStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {
    Appointment getAppointmentById(Long id);
    List<Appointment> getAppointmentsByUserId(Long userId, LocalDateTime startDate, LocalDateTime endDate);
    List<Appointment> getAppointmentsByUserIdAndStatus(Long userId, LocalDateTime startDate, LocalDateTime endDate, AppointmentStatus status);
    Appointment updateAppointment(Appointment appointment, User user);

    Appointment save(Appointment appointment);
}
