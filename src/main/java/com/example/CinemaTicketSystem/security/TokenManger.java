package com.example.CinemaTicketSystem.security;


import com.example.CinemaTicketSystem.Exception.SecMsg;
import com.example.CinemaTicketSystem.beans.Client;
import com.example.CinemaTicketSystem.beans.ClientType;
import com.example.CinemaTicketSystem.rep.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class TokenManger {


    private final Map<UUID, Information> map;
    private final ClientRepository clientRepository;

public UUID add(String email, String password) {
    Client fromDb = clientRepository.findTop1ByEmail(email);
    int clientId = fromDb.getId();
    removePreviousInstances(clientId);
    Information information = new Information();
    information.setUserId(clientId);
    information.setEmail(email);
    information.setType(fromDb.getClientType());
    information.setTime(LocalDateTime.now());
    UUID token = UUID.randomUUID();

    map.put(token, information);

    return token;
}


    public int getUserId(UUID token) {
        Information information = map.get(token);
        if (information == null) {
            throw new SecurityException(SecMsg.INVALID_TOKEN.getMsg());
        }
        return information.getUserId();
    }
    public ClientType getType (UUID token){
        Information information=map.get(token);
        if (information==null){
            throw new SecurityException(SecMsg.INVALID_TOKEN.getMsg());
        }
        return information.getType();
    }

    @Scheduled(fixedRate = 1000 * 60*120)
    public void deleteExpiredTokenOver30Minutes() {
        map.entrySet().removeIf(ins -> ins.getValue().getTime().isAfter(LocalDateTime.now().minusMinutes(120)));
    }

    public void removePreviousInstances(int userId){
        map.entrySet().removeIf(ins-> ins.getValue().getUserId()==userId);
    }

}
