package com.svi.group5.service;

import com.svi.group5.entity.Client;

import java.util.List;

public interface ClientService {
    Client findClientById(Long id);
    List<Client> findAllClients();
}
