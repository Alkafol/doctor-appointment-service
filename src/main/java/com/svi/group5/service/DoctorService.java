package com.svi.group5.service;

import com.svi.group5.entity.Doctor;
import com.svi.group5.entity.Position;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DoctorService {
    Doctor findDoctorById(Long id);
    List<Doctor> findAllDoctors();
    Doctor setDoctorPosition(Long doctorId, Long positionId);
    Position createPosition(String name);
}
