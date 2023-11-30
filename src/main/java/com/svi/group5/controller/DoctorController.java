package com.svi.group5.controller;

import com.svi.group5.dto.DoctorDataDto;
import com.svi.group5.dto.DoctorUpdateDto;
import com.svi.group5.dto.PositionCreateDto;
import com.svi.group5.dto.PositionDataDto;
import com.svi.group5.entity.Doctor;
import com.svi.group5.entity.Position;
import com.svi.group5.entity.User;
import com.svi.group5.service.DoctorService;
import com.svi.group5.service.PositionService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.svi.group5.mapper.UserMapper.convertToUserDataDto;

@RestController
@RequestMapping("/doctors")
@CrossOrigin
public class DoctorController {
    private final DoctorService doctorService;
    private final PositionService positionService;

    public DoctorController(DoctorService doctorService, PositionService positionService) {
        this.doctorService = doctorService;
        this.positionService = positionService;
    }

    @GetMapping("/{doctorId}")
    public DoctorDataDto findDoctorById(@PathVariable Long doctorId) {
        Doctor doctor = doctorService.findDoctorById(doctorId);
        return convertToDoctorDto(doctor);
    }

    @GetMapping
    public List<DoctorDataDto> findAllDoctors() {
        List<Doctor> doctors = doctorService.findAllDoctors();
        return doctors.stream().map(this::convertToDoctorDto).toList();
    }

    @PostMapping("/positions")
    public PositionDataDto createPosition(@RequestBody PositionCreateDto positionCreateDto) {
        Position position = convertToPosition(positionCreateDto);
        Position savedPosition = positionService.create(position);
        return convertToPositionDataDto(savedPosition);
    }

    @PutMapping
    public DoctorDataDto updateDoctor(@RequestBody DoctorUpdateDto doctorUpdateDto, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Doctor doctor = convertToDoctor(doctorUpdateDto);
        Doctor savedDoctor = doctorService.update(doctor, user);
        return convertToDoctorDto(savedDoctor);
    }

    private Doctor convertToDoctor(DoctorUpdateDto doctorUpdateDto) {
        Doctor doctor = new Doctor();
        Position position = positionService.findById(doctorUpdateDto.getPositionId());

        doctor.setId(doctorUpdateDto.getId());
        doctor.setFirstName(doctorUpdateDto.getFirstName());
        doctor.setLastName(doctorUpdateDto.getLastName());
        doctor.setMiddleName(doctorUpdateDto.getMiddleName());
        doctor.setDateOfBirth(doctorUpdateDto.getDateOfBirth());
        doctor.setEmail(doctorUpdateDto.getEmail());
        doctor.setPosition(position);

        return doctor;
    }

    private DoctorDataDto convertToDoctorDto(Doctor doctor) {
        return new DoctorDataDto(
                convertToUserDataDto(doctor),
                convertToPositionDataDto(doctor.getPosition())
        );
    }

    private PositionDataDto convertToPositionDataDto(Position position) {
        return new PositionDataDto(
                position.getId(),
                position.getName()
        );
    }

    private Position convertToPosition(PositionCreateDto positionCreateDto) {
        Position position = new Position();
        position.setName(positionCreateDto.getName());
        return position;
    }
}
