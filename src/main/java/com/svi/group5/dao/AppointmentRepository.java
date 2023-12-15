package com.svi.group5.dao;

import com.svi.group5.entity.Appointment;
import com.svi.group5.entity.Doctor;
import com.svi.group5.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT a from Appointment a WHERE (a.client.id = :userId or a.doctor.id = :userId) and :startDate < a.startTime and a.endTime < :endDate order by a.startTime")
    List<Appointment> findAppointmentByDateRange(@Param("userId") long userId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    @Query("SELECT a from Appointment a WHERE (a.client.id = :userId or a.doctor.id = :userId) and :startDate < a.startTime and a.endTime < :endDate and a.status = :status order by a.startTime")
    List<Appointment> findAppointmentByDateRangeAndStatus(@Param("userId") long userId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("status") AppointmentStatus status);
    Appointment findAppointmentByDoctorAndStartTimeOrderByStartTime(Doctor doctor, LocalDateTime startTime);
}
