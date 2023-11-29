package com.svi.group5.controller;

import com.svi.group5.dto.ClientDataDto;
import com.svi.group5.dto.ClientUpdateDto;
import com.svi.group5.entity.Client;
import com.svi.group5.entity.User;
import com.svi.group5.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    public ClientDataDto getClientById(@PathVariable Long clientId) {
        Client client = clientService.findClientById(clientId);
        return convertToClientDto(client);
    }

    @GetMapping
    public List<ClientDataDto> getAllClients() {
        List<Client> clients = clientService.findAllClients();
        return clients.stream().map(this::convertToClientDto).toList();
    }

    @PutMapping
    public ClientDataDto updateClient(ClientUpdateDto clientUpdateDto, Authentication authentication) {
        User user = (User) authentication.getCredentials();
        Client client = convertToClient(clientUpdateDto);
        Client updatedClient = clientService.updateClient(client, user);
        return convertToClientDto(updatedClient);
    }

    private ClientDataDto convertToClientDto(Client client) {
        return new ClientDataDto(convertToUserDataDto(client));
    }

    private Client convertToClient(ClientUpdateDto clientUpdateDto) {
        Client client = clientService.findClientById(clientUpdateDto.getId());

        client.setFirstName(clientUpdateDto.getFirstName());
        client.setLastName(clientUpdateDto.getLastName());
        client.setMiddleName(clientUpdateDto.getMiddleName());
        client.setEmail(client.getEmail());
        client.setDateOfBirth(client.getDateOfBirth());

        return client;
    }
}
