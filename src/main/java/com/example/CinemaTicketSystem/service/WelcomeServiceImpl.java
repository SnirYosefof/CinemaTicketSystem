package com.example.CinemaTicketSystem.service;

import com.example.CinemaTicketSystem.Exception.ErrMsg;
import com.example.CinemaTicketSystem.Exception.SecException;
import com.example.CinemaTicketSystem.Exception.SecMsg;
import com.example.CinemaTicketSystem.Exception.SysException;
import com.example.CinemaTicketSystem.beans.Client;
import com.example.CinemaTicketSystem.beans.ClientType;
import com.example.CinemaTicketSystem.rep.ClientRepository;
import com.example.CinemaTicketSystem.security.TokenManger;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

//Created by sniryosefof on 20 פבר׳
@Service
@RequiredArgsConstructor
public class WelcomeServiceImpl implements WelcomeService {

    private final ClientRepository clientRepository;
    private final TokenManger tokenManger;

    @Override
    public void register(String name, String email, String password) throws SysException {
        Client client = Client.builder()
                .name(name)
                .email(email)
                .password(password)
                .clientType(ClientType.Client)
                .build();
        if (clientRepository.existsByEmail(email)) {
            throw new SysException(ErrMsg.CLIENT_EMAIL);
        }
        clientRepository.save(client);
    }

    @Override
    public UUID login(String email, String password) throws SecException {
        if (!clientRepository.existsByEmailAndPassword(email, password)) {
            throw new SecException(SecMsg.EMAIL_OR_PASSWORD_INCORRECT);
        }
        return tokenManger.add(email, password);
    }
}
