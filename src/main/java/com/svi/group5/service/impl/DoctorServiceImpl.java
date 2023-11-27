package com.svi.group5.service.impl;

import com.svi.group5.entity.Doctor;
import com.svi.group5.entity.Position;
import com.svi.group5.service.DoctorService;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Override
    public Doctor getDoctorById(long id) {
        return null;
    }

    @Override
    public Doctor setDoctorPosition(long doctorId, long positionId) {
        return null;
    }

    @Override
    public Position createPosition(String name) {
        return null;
    }
}
