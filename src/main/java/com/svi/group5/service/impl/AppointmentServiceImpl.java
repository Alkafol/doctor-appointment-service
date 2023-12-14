package com.svi.group5.service.impl;

import com.svi.group5.dao.AppointmentRepository;
import com.svi.group5.entity.Appointment;
import com.svi.group5.entity.User;
import com.svi.group5.enums.AppointmentStatus;
import com.svi.group5.enums.Role;
import com.svi.group5.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Appointment with id = " + id + " not found"));
    }

    @Override
    public List<Appointment> getAppointmentsByUserId(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        return appointmentRepository.findAppointmentByDateRange(userId, startDate, endDate);
    }

    @Override
    public List<Appointment> getAppointmentsByUserIdAndStatus(Long userId, LocalDateTime startDate, LocalDateTime endDate, AppointmentStatus status) {
        if (status == null) {
            return appointmentRepository.findAppointmentByDateRange(userId, startDate, endDate);
        }
        return appointmentRepository.findAppointmentByDateRangeAndStatus(userId, startDate, endDate, status);
    }

    @Override
    public Appointment updateAppointment(Appointment appointment, User user) {
        if (user.getRole() == Role.CLIENT) {
            if (!Objects.equals(user.getId(), appointment.getClient().getId())) {
                throw new IllegalStateException();
            }

            if (appointment.getStatus() == AppointmentStatus.CLOSED) {
                throw new IllegalStateException();
            }
        }
        if (user.getRole() == Role.DOCTOR) {
            if (!Objects.equals(user.getId(), appointment.getDoctor().getId())) {
                throw new IllegalStateException();
            }

            if (appointment.getStatus() == AppointmentStatus.BOOKED) {
                throw new IllegalStateException();
            }
        }

        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }
}
