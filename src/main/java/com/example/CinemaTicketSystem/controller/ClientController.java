package com.example.CinemaTicketSystem.controller;

import com.example.CinemaTicketSystem.Exception.SecException;
import com.example.CinemaTicketSystem.Exception.SecMsg;
import com.example.CinemaTicketSystem.Exception.SysException;
import com.example.CinemaTicketSystem.beans.Client;
import com.example.CinemaTicketSystem.beans.ClientType;
import com.example.CinemaTicketSystem.beans.Movie;
import com.example.CinemaTicketSystem.security.TokenManger;
import com.example.CinemaTicketSystem.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

//Created by sniryosefof on 01 מרץ
@RestController
@AllArgsConstructor
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;
    private final TokenManger tokenManger;

    @PostMapping("/{row}/{col}")
    public void purchaseScreen(@RequestHeader("Authorization") UUID token, @PathVariable int row, @PathVariable int col, @RequestBody Movie movie) throws SecException, SysException {
        if (tokenManger.getType(token) != ClientType.Client) {
            throw new SecException(SecMsg.TYPE_UNCORRECTED);
        }
        int customerId = tokenManger.getUserId(token);

        clientService.purchaseScreen(customerId, row, col, movie);
    }

    @GetMapping()
    public List<Client> getAllClient(@RequestHeader("Authorization") UUID token) throws SecException {
        if (tokenManger.getType(token) != ClientType.Admin) {
            throw new SecException(SecMsg.TYPE_UNCORRECTED);
        }
        return clientService.getAllClient();
    }

    @GetMapping("/byName")
    public Client findClientByName(@RequestHeader("Authorization") UUID token, @RequestParam String name) throws SecException, SysException {
        if (tokenManger.getType(token) != ClientType.Admin) {
            throw new SecException(SecMsg.TYPE_UNCORRECTED);
        }
        return clientService.findClientByName(name);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@RequestHeader("Authorization") UUID token, @PathVariable int id) throws SecException, SysException {
        if (tokenManger.getType(token) != ClientType.Admin) {
            throw new SecException(SecMsg.TYPE_UNCORRECTED);
        }
        clientService.deleteClient(id);
    }
}
