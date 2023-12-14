package com.svi.group5.service;

import com.svi.group5.entity.Doctor;
import com.svi.group5.entity.User;

import java.util.List;
import java.util.Map;

public interface DoctorService {
    Doctor findDoctorById(Long id);
    List<Doctor> findAllDoctors(Map<String, Object> filter);
    Doctor update(Doctor doctor, User user);
}
