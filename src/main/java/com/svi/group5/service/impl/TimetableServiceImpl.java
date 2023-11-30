package com.svi.group5.service.impl;

import com.svi.group5.dao.AppointmentRepository;
import com.svi.group5.dao.DoctorRepository;
import com.svi.group5.entity.Appointment;
import com.svi.group5.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.svi.group5.enums.AppointmentStatus.AVAILABLE;

@Service
public class TimetableServiceImpl {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;

    private static final int workDayStart = 9;
    private static final int workDayEnd = 18;
    private static final int appointmentDurationInMinutes = 15;
    private static final String startAppointmentGeneration = "0 0 1 * * MON";

    @Autowired
    public TimetableServiceImpl(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
    }

    @Scheduled(cron = "0 0 1 * * MON")
    public void setupAppointmentTimetable() {
        List<Doctor> allDoctors = doctorRepository.findAll();

        for (Doctor doctor : allDoctors) {
            List<Appointment> appointmentList = createAppointmentPool(doctor, LocalDateTime.now(), LocalDateTime.now().plusDays(7));
            appointmentRepository.saveAll(appointmentList);
        }
    }

    private List<Appointment> createAppointmentPool(Doctor doctor, LocalDateTime startTime, LocalDateTime endTime) {
        List<Appointment> result = new ArrayList<>();

        LocalDateTime dayStart = startTime.toLocalDate().atStartOfDay();
        int daysNum = endTime.getDayOfYear() - startTime.getDayOfYear();

        for (int i = 0; i < daysNum; i++) {
            LocalDateTime currentWorkDayStart = dayStart.plusHours(workDayStart);

            for (int j = 0; i < (workDayEnd - workDayStart) * (60 / appointmentDurationInMinutes); i++) {
                LocalDateTime appointmentStart = currentWorkDayStart;
                LocalDateTime appointmentEnd = currentWorkDayStart.plusMinutes(appointmentDurationInMinutes);

                Appointment temp = new Appointment();
                temp.setDoctor(doctor);
                temp.setStartTime(appointmentStart);
                temp.setEndTime(appointmentEnd);
                temp.setStatus(AVAILABLE);

                result.add(temp);

                currentWorkDayStart = appointmentEnd;
            }

            dayStart = dayStart.plusDays(1);
        }

        return result;
    }

}
