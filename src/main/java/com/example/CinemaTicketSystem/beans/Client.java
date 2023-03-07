package com.example.CinemaTicketSystem.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


//Created by sniryosefof on 24 ינו׳
@Entity
@Table(name = "client")
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private ClientType clientType;
    private String name;
    private String email;
    private String password;


}
