package com.mcb.javajuniortask.service;

import com.mcb.javajuniortask.dto.ClientDTO;
import com.mcb.javajuniortask.model.Client;
import com.mcb.javajuniortask.model.Debt;
import com.mcb.javajuniortask.model.Payment;
import com.mcb.javajuniortask.repository.ClientRepository;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@ShellComponent
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @ShellMethod("Shows all clients in db")
    @Transactional
    public Iterable<ClientDTO> showAllClients() {
        return StreamSupport.stream(clientRepository.findAll().spliterator(), false).map(client -> {
            ClientDTO clientDTO = new ClientDTO();
            clientDTO.setId(client.getId());
            clientDTO.setName(client.getName());
            clientDTO.setTotalDebt(client.getDebts().stream().map(Debt::getValue).reduce(BigDecimal::add).orElse(BigDecimal.ZERO)
                    .subtract(client.getPayts().stream().map(Payment::getValue).reduce(BigDecimal::add).orElse(BigDecimal.ZERO)));
            return clientDTO;
        }).collect(Collectors.toList());
    }

    @ShellMethod("Adds client to db")
    @Transactional
    public UUID addClient(@ShellOption String name) {
        Client client = new Client();
        client.setName(name);
        client.setId(UUID.randomUUID());
        client = clientRepository.save(client);
        return client.getId();
    }

    @ShellMethod("Adds debt to client")
    @Transactional
    public UUID addDebtToClient(@ShellOption UUID clientId, @ShellOption BigDecimal value) {
        Client client = clientRepository.findOne(clientId);
        Debt debt = new Debt();
        debt.setValue(value);
        debt.setId(UUID.randomUUID());
        debt.setClient(client);
        client.getDebts().add(debt);
        clientRepository.save(client);
        return debt.getId();
    }

    @ShellMethod("Pays for client debt")
    @Transactional
    public UUID payForClientDebt(@ShellOption UUID clientId, @ShellOption BigDecimal value) {
        Client client = clientRepository.findOne(clientId);
        Payment payt = new Payment();

        payt.setValue(value);
        payt.setId(UUID.randomUUID());
        payt.setClient(client);

        client.getPayts().add(payt);
        clientRepository.save(client);

        return payt.getId();
    }
}
