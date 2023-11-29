package com.svi.group5.controller;

import com.svi.group5.dto.ClientDto;
import com.svi.group5.entity.Client;
import com.svi.group5.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.svi.group5.mapper.UserMapper.convertToUserDataDto;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{clientId}")
    public ClientDto getClientById(@PathVariable Long clientId) {
        Client client = clientService.findClientById(clientId);
        return convertToClientDto(client);
    }

    @GetMapping
    public List<ClientDto> getAllClients() {
        List<Client> clients = clientService.findAllClients();
        return clients.stream().map(this::convertToClientDto).toList();
    }


    private ClientDto convertToClientDto(Client client) {
        return new ClientDto(convertToUserDataDto(client));
    }

}
