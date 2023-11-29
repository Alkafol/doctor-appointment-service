package com.svi.group5.service;

import com.svi.group5.entity.Client;
import com.svi.group5.entity.User;

import java.util.List;

public interface ClientService {
    Client findClientById(Long id);
    List<Client> findAllClients();
    Client updateClient(Client client, User user);
}
