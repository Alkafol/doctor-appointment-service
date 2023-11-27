package com.svi.group5.service.impl;

import com.svi.group5.dto.AppointmentDto;
import com.svi.group5.dto.DoctorDto;
import com.svi.group5.dto.PositionDto;
import com.svi.group5.entity.Doctor;
import com.svi.group5.service.DoctorService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Override
    public DoctorDto getDoctorById(long id) {
        return null;
    }

    @Override
    public List<DoctorDto> getAllDoctors() {
        return null;
    }

    @Override
    public List<AppointmentDto> getDayAppointments(long doctorId, Date date) {
        return null;
    }

    @Override
    public List<AppointmentDto> getWeekAppointments(long doctorId, Date startDate, Date endDate) {
        return null;
    }

    @Override
    public AppointmentDto closeAppointment(long appointmentId) {
        return null;
    }

    @Override
    public DoctorDto setDoctorPosition(long doctorId, long positionId) {
        return null;
    }

    @Override
    public PositionDto createPosition(String name) {
        return null;
    }
}
