package com.svi.group5.service;

import com.svi.group5.entity.Appointment;

import java.time.LocalDate;
import java.util.Set;

public interface AppointmentService {
    Appointment findAppointmentById(Long id);
    Set<Appointment> findAppointmentsByUserId(Long userId);
    Set<Appointment> findAppointmentsByUserId(Long userId, LocalDate startDate, LocalDate endDate);
    Appointment updateAppointment(Appointment appointment);
}
