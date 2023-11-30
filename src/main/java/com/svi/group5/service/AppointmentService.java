package com.svi.group5.service;

import com.svi.group5.entity.Appointment;
import com.svi.group5.entity.User;

import java.time.LocalDate;
import java.util.Set;

public interface AppointmentService {
    Appointment getAppointmentById(Long id);
    Set<Appointment> getAppointmentsByUserId(Long userId);
    Set<Appointment> getAppointmentsByUserId(Long userId, LocalDate startDate, LocalDate endDate);
    Appointment updateAppointment(Appointment appointment, User user);

    Appointment save(Appointment appointment);
}
