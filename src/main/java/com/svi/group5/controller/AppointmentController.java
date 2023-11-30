package com.svi.group5.controller;

import com.svi.group5.dto.AppointmentCreateDto;
import com.svi.group5.dto.AppointmentDataDto;
import com.svi.group5.dto.AppointmentUpdateDto;
import com.svi.group5.entity.Appointment;
import com.svi.group5.entity.Client;
import com.svi.group5.entity.Doctor;
import com.svi.group5.entity.User;
import com.svi.group5.enums.AppointmentStatus;
import com.svi.group5.service.AppointmentService;
import com.svi.group5.service.ClientService;
import com.svi.group5.service.DoctorService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import static com.svi.group5.mapper.UserMapper.convertToUserDataDto;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final ClientService clientService;
    private final DoctorService doctorService;

    public AppointmentController(AppointmentService appointmentService, ClientService clientService, DoctorService doctorService) {
        this.appointmentService = appointmentService;
        this.clientService = clientService;
        this.doctorService = doctorService;
    }

    @PostMapping
    public AppointmentDataDto createAppointment(@RequestBody AppointmentCreateDto appointmentCreateDto) {
        Appointment appointment = convertToAppointment(appointmentCreateDto);
        Appointment savedAppointment = appointmentService.save(appointment);
        return convertToAppointmentDto(savedAppointment);
    }

    @GetMapping("/{appointmentId}")
    public AppointmentDataDto getAppointmentById(@PathVariable Long appointmentId) {
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);
        return convertToAppointmentDto(appointment);
    }

    @GetMapping("/user/{userId}")
    public Set<AppointmentDataDto> getAppointmentsByUserId(
            @PathVariable Long userId,
            @DateTimeFormat(pattern = "yyyyMMdd")
            @RequestParam LocalDate startDate,
            @DateTimeFormat(pattern = "yyyyMMdd")
            @RequestParam LocalDate endDate
    ) {
        Set<Appointment> appointments = appointmentService.getAppointmentsByUserId(userId, startDate, endDate);
        return appointments.stream()
                .map(this::convertToAppointmentDto)
                .collect(Collectors.toSet());
    }

    @GetMapping("/user/{userId}/status")
    public Set<AppointmentDataDto> getAppointmentsByUserIdAndStatus(
            @PathVariable Long userId,
            @DateTimeFormat(pattern = "yyyyMMdd")
            @RequestParam LocalDate startDate,
            @DateTimeFormat(pattern = "yyyyMMdd")
            @RequestParam LocalDate endDate,
            @RequestParam AppointmentStatus status
    ) {
        Set<Appointment> appointments = appointmentService.getAppointmentsByUserIdAndStatus(userId, startDate, endDate, status);
        return appointments.stream()
                .map(this::convertToAppointmentDto)
                .collect(Collectors.toSet());
    }

    @PutMapping
    public AppointmentDataDto update(@RequestBody AppointmentUpdateDto appointmentUpdateDto, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Appointment appointment = convertToAppointment(appointmentUpdateDto);
        Appointment savedAppointment = appointmentService.updateAppointment(appointment, user);
        return convertToAppointmentDto(savedAppointment);
    }

    private AppointmentDataDto convertToAppointmentDto(Appointment appointment) {
        return new AppointmentDataDto(
                appointment.getId(),
                convertToUserDataDto(appointment.getClient()),
                convertToUserDataDto(appointment.getDoctor()),
                appointment.getStartTime(),
                appointment.getEndTime(),
                appointment.getStatus()
        );
    }

    private Appointment convertToAppointment(AppointmentUpdateDto appointmentUpdateDto) {
        Appointment appointment = appointmentService.getAppointmentById(appointmentUpdateDto.getId());
        Client client = clientService.findClientById(appointmentUpdateDto.getClientId());

        appointment.setId(appointmentUpdateDto.getId());
        appointment.setClient(client);
        appointment.setStatus(appointmentUpdateDto.getStatus());

        return appointment;
    }

    private Appointment convertToAppointment(AppointmentCreateDto appointmentCreateDto) {
        Appointment appointment = new Appointment();
        Doctor doctor = doctorService.findDoctorById(appointmentCreateDto.getDoctorId());

        appointment.setDoctor(doctor);
        appointment.setStartTime(appointmentCreateDto.getStartTime());
        appointment.setEndTime(appointmentCreateDto.getEndTime());
        appointment.setStatus(AppointmentStatus.AVAILABLE);

        return appointment;
    }
}
