package com.svi.group5.service.impl;

import com.svi.group5.dao.DoctorRepository;
import com.svi.group5.entity.Doctor;
import com.svi.group5.entity.User;
import com.svi.group5.service.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    public Doctor update(Doctor doctor, User user) {
        if (!Objects.equals(user.getId(), doctor.getId())) {
            throw new IllegalStateException();
        }

        return doctorRepository.save(doctor);
    }
}
