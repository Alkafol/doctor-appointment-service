package com.svi.group5.service.impl;

import com.svi.group5.dao.AppointmentRepository;
import com.svi.group5.dao.DoctorRepository;
import com.svi.group5.entity.Appointment;
import com.svi.group5.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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


    public void createEmptySlotsForADay(Doctor doctor, LocalDate day) {
        Stream.iterate(day.atStartOfDay().plusHours(workDayStart), (LocalDateTime prevSlotStart) -> prevSlotStart.plusMinutes(appointmentDurationInMinutes))
            .takeWhile((LocalDateTime slotStart) -> slotStart.getHour() < workDayEnd)
            .map((LocalDateTime slotStart) -> makeEmptySlot(doctor, slotStart))
            .forEach(this::createIfNotExists);
    }

    public Appointment makeEmptySlot(Doctor doctor, LocalDateTime startTime) {
        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setStartTime(startTime);
        appointment.setEndTime(startTime.plusMinutes(appointmentDurationInMinutes));
        appointment.setStatus(AVAILABLE);
        return appointment;
    }

    public void createIfNotExists(Appointment appointment) {
        Appointment existing = appointmentRepository.findAppointmentByDoctorAndStartTime(appointment.getDoctor(), appointment.getStartTime());
        if (existing != null) {
            return;
        }
        appointmentRepository.save(appointment);
    }

    public void ensureThisWeekSchedule() {
        LocalDate monday = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        List<LocalDate> thisWeek = IntStream.range(0, 7).mapToObj(offset -> monday.plusDays(offset)).toList();
        List<Doctor> doctors = doctorRepository.findAll();
        for (Doctor doctor : doctors) {
            for (LocalDate day : thisWeek) {
                createEmptySlotsForADay(doctor, day);
            }
        }
    }

}
