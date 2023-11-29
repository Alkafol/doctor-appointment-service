package com.svi.group5.service.impl;

import com.svi.group5.dao.ClientRepository;
import com.svi.group5.entity.Client;
import com.svi.group5.entity.User;
import com.svi.group5.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client findClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User with id = " + id + " not found"));
    }

    @Override
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client updateClient(Client client, User user) {
        return null;
    }
}
