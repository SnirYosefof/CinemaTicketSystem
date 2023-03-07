package com.example.CinemaTicketSystem.controller;

import com.example.CinemaTicketSystem.Exception.SecException;
import com.example.CinemaTicketSystem.Exception.SysException;
import com.example.CinemaTicketSystem.beans.ClientType;
import com.example.CinemaTicketSystem.dto.LoginReqDto;
import com.example.CinemaTicketSystem.dto.LoginResDto;
import com.example.CinemaTicketSystem.dto.RegisterReqDto;
import com.example.CinemaTicketSystem.rep.ClientRepository;
import com.example.CinemaTicketSystem.service.WelcomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

//Created by sniryosefof on 22 פבר׳
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/welcome")

public class WelcomeController {
    private final WelcomeService welcomeService;
    private final ClientRepository clientRepository;

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody RegisterReqDto registerReqDto) throws SysException {
        String email = registerReqDto.getEmail();
        String password = registerReqDto.getPassword();
        String name = registerReqDto.getName();
        welcomeService.register(name, email, password);
    }

    @PostMapping("login")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResDto login(@Valid @RequestBody LoginReqDto loginReqDto) throws SecException {
        String email = loginReqDto.getEmail();
        String password = loginReqDto.getPassword();
        UUID token = welcomeService.login(email, password);
        String name = clientRepository.findNameByEmail(email);
        ClientType type = clientRepository.findTypeByEmail(email);
        return new LoginResDto(token, email, name, type);
    }
}
