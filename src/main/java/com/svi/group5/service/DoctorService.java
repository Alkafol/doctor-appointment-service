package com.svi.group5.service;

import com.svi.group5.entity.Doctor;
import com.svi.group5.entity.Position;

import java.util.List;

public interface DoctorService {
    Doctor findDoctorById(Long id);
    List<Doctor> findAllDoctors();
    Doctor updateDoctor(Doctor doctor);
    Position savePosition(Position position);
}
