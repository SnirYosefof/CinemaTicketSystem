package com.example.CinemaTicketSystem.service;

import com.example.CinemaTicketSystem.Exception.SysException;
import com.example.CinemaTicketSystem.beans.Client;
import com.example.CinemaTicketSystem.beans.Movie;

import java.util.List;
import java.util.UUID;

//Created by sniryosefof on 20 פבר׳
public interface ClientService {

    UUID purchaseScreen(int clientId, int row, int col, Movie movie) throws SysException;

    List<Client> getAllClient();

    Client findClientByName(String name) throws SysException;

    void deleteClient(int id) throws SysException;
}
