package com.svi.group5.service.impl;

import com.svi.group5.dao.DoctorRepository;
import com.svi.group5.entity.Doctor;
import com.svi.group5.entity.Position;
import com.svi.group5.service.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor findDoctorById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Doctor> findAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor setDoctorPosition(Long doctorId, Long positionId) {
        return null;
    }

    @Override
    public Position createPosition(String name) {
        return null;
    }
}
