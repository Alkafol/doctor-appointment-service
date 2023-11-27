package com.svi.group5.service;

import com.svi.group5.dto.AppointmentDto;
import com.svi.group5.dto.ClientDto;

import java.util.Date;
import java.util.List;

public interface ClientService {
    ClientDto getClientById(long id);
    List<AppointmentDto> getDayAppointments(long client_id, Date date);
    List<AppointmentDto> getAppointments(long client_id, Date startDate, Date endDate);
    boolean bookAppointment(long client_id, long appointmentId);
}
