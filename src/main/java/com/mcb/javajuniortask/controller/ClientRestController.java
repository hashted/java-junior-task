package com.mcb.javajuniortask.controller;

import com.mcb.javajuniortask.dto.ClientDTO;
import com.mcb.javajuniortask.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/rest")
public class ClientRestController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/clients/show/", method = RequestMethod.GET)
    public Iterable<ClientDTO> showAllClients() {
        return clientService.showAllClients();
    }

    @RequestMapping(value = "/clients/add/{name}/", method = RequestMethod.GET)
    public UUID addClient(@PathVariable String name) {
        return clientService.addClient(name);
    }

    @RequestMapping(value = "/clients/{id}/debt/{value}/", method = RequestMethod.GET)
    public UUID addDebtToClient(@PathVariable UUID id, @PathVariable BigDecimal value) {
        return clientService.addDebtToClient(id, value);
    }

    @RequestMapping(value = "/clients/{id}/payt/{value}/", method = RequestMethod.GET)
    public UUID payForClientDebt(@PathVariable UUID id, @PathVariable BigDecimal value) {
        return clientService.payForClientDebt(id, value);
    }
}
