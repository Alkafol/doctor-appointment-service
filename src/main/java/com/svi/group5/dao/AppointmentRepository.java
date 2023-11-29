package com.svi.group5.dao;

import com.svi.group5.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Set<Appointment> findAppointmentByDoctorIdOrClientId(Long doctorId, Long clientId);
    @Query("SELECT a from Appointment a WHERE (a.client.id = :userId or a.doctor.id = :userId) and :startDate < a.startTime and a.endTime < :endDate")
    Set<Appointment> findAppointmentByDateRange(@Param("userId") long userId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
