package com.svi.group5.controller;

import com.svi.group5.dto.*;
import com.svi.group5.entity.Doctor;
import com.svi.group5.entity.Position;
import com.svi.group5.entity.User;
import com.svi.group5.service.DoctorService;
import com.svi.group5.service.PositionService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping()
    public List<DoctorDataDto> findAllDoctors(@RequestBody(required = false) DoctorFilterDto filterDto) {
        Map<String, Object> filter = convertFiterToMap(filterDto);
        List<Doctor> doctors = doctorService.findAllDoctors(filter);
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
        Doctor doctor = doctorService.findDoctorById(doctorUpdateDto.getId());

        doctor.setId(doctorUpdateDto.getId());
        doctor.setFirstName(doctorUpdateDto.getFirstName());
        doctor.setLastName(doctorUpdateDto.getLastName());
        doctor.setMiddleName(doctorUpdateDto.getMiddleName());
        doctor.setDateOfBirth(doctorUpdateDto.getDateOfBirth());
        doctor.setEmail(doctorUpdateDto.getEmail());
        if (doctorUpdateDto.getPositionId() != null) {
            Position position = positionService.findById(doctorUpdateDto.getPositionId());
            doctor.setPosition(position);
        }
        doctor.setDescription(doctorUpdateDto.getDescription());
        doctor.setExperience(doctorUpdateDto.getExperience());

        return doctor;
    }

    private DoctorDataDto convertToDoctorDto(Doctor doctor) {
        return new DoctorDataDto(
                convertToUserDataDto(doctor),
                convertToPositionDataDto(doctor.getPosition()),
                doctor.getDescription(),
                doctor.getExperience()
        );
    }

    private PositionDataDto convertToPositionDataDto(Position position) {
        if (position == null) return null;
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

    private Map<String, Object> convertFiterToMap(DoctorFilterDto doctorFilterDto) {
        HashMap<String, Object> filterMap = new HashMap<>();
        if (doctorFilterDto == null) {
            return filterMap;
        }
        if (doctorFilterDto.getExperienceLowerBound() != null) {
            filterMap.put("experienceLowerBound", doctorFilterDto.getExperienceLowerBound());
        }
        if (doctorFilterDto.getPositionId() != null) {
            filterMap.put("positionId", doctorFilterDto.getPositionId());
        }

        return filterMap;
    }
}
