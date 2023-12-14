package com.svi.group5.dao;

import com.svi.group5.entity.Doctor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DoctorRepositoryCustom {
    List<Doctor> find(Map<String, Object> filter);
}
