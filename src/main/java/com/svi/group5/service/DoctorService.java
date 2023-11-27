package com.svi.group5.service;

import com.svi.group5.dto.AppointmentDto;
import com.svi.group5.dto.DoctorDto;
import com.svi.group5.dto.PositionDto;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface DoctorService {
    DoctorDto getDoctorById(long id);
    List<DoctorDto> getAllDoctors();
    List<AppointmentDto> getDayAppointments(long doctorId, Date date);
    List<AppointmentDto> getWeekAppointments(long doctorId, Date startDate, Date endDate);
    AppointmentDto closeAppointment(long appointmentId);
    DoctorDto setDoctorPosition(long doctorId, long positionId);
    PositionDto createPosition(String name);
}
