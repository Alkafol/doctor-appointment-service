package com.svi.group5.service.impl;

import com.svi.group5.dao.AppointmentRepository;
import com.svi.group5.entity.Appointment;
import com.svi.group5.enums.AppointmentStatus;
import com.svi.group5.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
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
    public Appointment getAppointmentById(long id) {
        return appointmentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Appointment with id = " + id + " not found"));
    }

    //TODO Исправить как появится что-то на фронте
    @Override
    public Set<Appointment> getAppointmentsByUserId(long userId) {
        return appointmentRepository.findAppointmentByDoctorIdOrClientId(userId, userId);
    }

    @Override
    public Set<Appointment> getAppointmentsByUserId(long userId, LocalDate startDate, LocalDate endDate) {
        return appointmentRepository.findAppointmentByDateRange(userId, startDate, endDate);
    }

    @Override
    public Appointment changeAppointmentStatus(long id, AppointmentStatus status) {
        Appointment result = getAppointmentById(id);
        result.setStatus(status);
        return appointmentRepository.save(result);
    }
}
