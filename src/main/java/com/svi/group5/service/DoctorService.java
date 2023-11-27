package com.svi.group5.service;

import com.svi.group5.entity.Doctor;
import com.svi.group5.entity.Position;
import org.springframework.stereotype.Service;

@Service
public interface DoctorService {
    Doctor getDoctorById(long id);
    Doctor setDoctorPosition(long doctorId, long positionId);
    Position createPosition(String name);
}
