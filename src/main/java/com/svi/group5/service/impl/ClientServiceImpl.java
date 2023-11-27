package com.svi.group5.service.impl;

import com.svi.group5.dao.ClientRepository;
import com.svi.group5.dto.AppointmentDto;
import com.svi.group5.dto.ClientDto;
import com.svi.group5.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDto getClientById(long id) {
        return clientRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User with id" + id + " not found"));
    }

    @Override
    public List<AppointmentDto> getDayAppointments(long client_id, Date date) {
        return null;
    }

    @Override
    public List<AppointmentDto> getAppointments(long client_id, Date startDate, Date endDate) {
        return null;
    }

    @Override
    public boolean bookAppointment(long client_id, long appointmentId) {
        return false;
    }
}
