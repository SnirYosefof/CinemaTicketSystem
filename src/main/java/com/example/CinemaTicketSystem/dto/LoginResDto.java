package com.example.CinemaTicketSystem.dto;

import com.example.CinemaTicketSystem.beans.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

//Created by sniryosefof on 22 פבר׳
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResDto {
    private UUID token;
    private String email;
    private String name;
    private ClientType type;

}
