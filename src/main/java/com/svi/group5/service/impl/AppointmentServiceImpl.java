package com.svi.group5.service.impl;

import com.svi.group5.dao.AppointmentRepository;
import com.svi.group5.entity.Appointment;
import com.svi.group5.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment findAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    @Override
    public Set<Appointment> findAppointmentsByUserId(Long userId) {
        return appointmentRepository.findAppointmentByDoctorIdOrClientId(userId, userId);
    }

    @Override
    public Set<Appointment> findAppointmentsByUserId(Long userId, LocalDate startDate, LocalDate endDate) {
        return appointmentRepository.findAppointmentByDateRange(userId, startDate, endDate);
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        // TODO: check all invariants before update
        return appointmentRepository.save(appointment);
    }
}
