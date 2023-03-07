package com.example.CinemaTicketSystem.security;

import com.example.CinemaTicketSystem.beans.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
//Created by sniryosefof on 21 פבר׳
public class Information {
    private int userId;
    private ClientType type;
    private LocalDateTime time;
    private String email;
}
