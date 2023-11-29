package com.svi.group5.service.impl;

import com.svi.group5.dao.DoctorRepository;
import com.svi.group5.dao.PositionRepository;
import com.svi.group5.entity.Doctor;
import com.svi.group5.entity.Position;
import com.svi.group5.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final PositionRepository positionRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, PositionRepository positionRepository) {
        this.doctorRepository = doctorRepository;
        this.positionRepository = positionRepository;
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
    public Doctor updateDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Position savePosition(Position position) {
        return positionRepository.save(position);
    }
}
