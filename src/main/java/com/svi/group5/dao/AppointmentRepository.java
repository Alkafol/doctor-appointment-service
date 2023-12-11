package com.svi.group5.dao;

import com.svi.group5.entity.Appointment;
import com.svi.group5.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Set;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT a from Appointment a WHERE (a.client.id = :userId or a.doctor.id = :userId) and :startDate < a.startTime and a.endTime < :endDate")
    Set<Appointment> findAppointmentByDateRange(@Param("userId") long userId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    @Query("SELECT a from Appointment a WHERE (a.client.id = :userId or a.doctor.id = :userId) and :startDate < a.startTime and a.endTime < :endDate and a.status = :status")
    Set<Appointment> findAppointmentByDateRangeAndStatus(@Param("userId") long userId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("status") AppointmentStatus status);
}
