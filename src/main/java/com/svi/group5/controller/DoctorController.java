package com.svi.group5.controller;

import com.svi.group5.dto.DoctorDto;
import com.svi.group5.dto.PositionDto;
import com.svi.group5.entity.Doctor;
import com.svi.group5.entity.Position;
import com.svi.group5.service.DoctorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.svi.group5.mapper.UserMapper.convertToUserDataDto;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/{doctorId}")
    public DoctorDto findDoctorById(@PathVariable Long doctorId) {
        Doctor doctor = doctorService.findDoctorById(doctorId);
        return convertToDoctorDto(doctor);
    }

    @GetMapping
    public List<DoctorDto> findAllDoctors() {
        List<Doctor> doctors = doctorService.findAllDoctors();
        return doctors.stream().map(this::convertToDoctorDto).toList();
    }

    private DoctorDto convertToDoctorDto(Doctor doctor) {
        return new DoctorDto(
                convertToUserDataDto(doctor),
                convertToPositionDto(doctor.getPosition())
        );
    }

    private PositionDto convertToPositionDto(Position position) {
        return new PositionDto(
                position.getId(),
                position.getName()
        );
    }
}
