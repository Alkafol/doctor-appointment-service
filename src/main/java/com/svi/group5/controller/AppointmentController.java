package com.svi.group5.controller;

import com.svi.group5.dto.AppointmentDataDto;
import com.svi.group5.dto.AppointmentUpdateDto;
import com.svi.group5.entity.Appointment;
import com.svi.group5.entity.Client;
import com.svi.group5.entity.Doctor;
import com.svi.group5.service.AppointmentService;
import com.svi.group5.service.ClientService;
import com.svi.group5.service.DoctorService;
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

    @GetMapping("/{appointmentId}")
    public AppointmentDataDto getAppointmentById(@PathVariable Long appointmentId) {
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);
        return convertToAppointmentDto(appointment);
    }

    @GetMapping("/user/{userId}")
    public Set<AppointmentDataDto> getAppointmentsByUserId(
            @PathVariable Long userId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ) {
        Set<Appointment> appointments = appointmentService.getAppointmentsByUserId(userId, startDate, endDate);
        return appointments.stream()
                .map(this::convertToAppointmentDto)
                .collect(Collectors.toSet());
    }

    @PutMapping
    public AppointmentDataDto update(@RequestBody AppointmentUpdateDto appointmentUpdateDto) {
        Appointment appointment = convertToAppointment(appointmentUpdateDto);
        Appointment savedAppointment = appointmentService.updateAppointment(appointment);
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
        Appointment appointment = new Appointment();
        Client client = clientService.findClientById(appointmentUpdateDto.getClientId());
        Doctor doctor = doctorService.findDoctorById(appointmentUpdateDto.getDoctorId());

        appointment.setId(appointmentUpdateDto.getId());
        appointment.setClient(client);
        appointment.setDoctor(doctor);
        appointment.setStatus(appointmentUpdateDto.getStatus());

        return appointment;
    }
}
