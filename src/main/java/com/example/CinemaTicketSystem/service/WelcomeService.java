package com.example.CinemaTicketSystem.service;

import com.example.CinemaTicketSystem.Exception.SecException;
import com.example.CinemaTicketSystem.Exception.SysException;

import java.util.UUID;

//Created by sniryosefof on 20 פבר׳
public interface WelcomeService {
    void register(String name, String email, String password) throws SysException;

    UUID login(String email, String password) throws SecException;
}
