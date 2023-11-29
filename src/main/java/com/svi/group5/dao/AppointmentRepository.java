package com.svi.group5.dao;

import com.svi.group5.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Set<Appointment> findAppointmentByDoctorIdOrClientId(Long doctorId, Long clientId);
    @Query("SELECT * from Appointment WHERE id = userId and startTime < endDate and endTime > startDate")
    Set<Appointment> findAppointmentByDateRange(long userId, LocalDate startDate, LocalDate endDate);
}
