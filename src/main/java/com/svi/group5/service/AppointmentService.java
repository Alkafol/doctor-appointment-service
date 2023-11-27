package com.svi.group5.service;

import com.svi.group5.entity.Appointment;
import com.svi.group5.enums.AppointmentStatus;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

public interface AppointmentService {
    Appointment getAppointmentById(long id);
    Set<Appointment> getAppointmentsByUserId(long userId);
    Set<Appointment> getAppointmentsByUserId(long userId, LocalDate startDate, LocalDate endDate);
    Appointment changeAppointmentStatus(long id, AppointmentStatus status);
}
