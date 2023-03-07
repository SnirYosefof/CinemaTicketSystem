package com.example.CinemaTicketSystem.beans;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

//Created by sniryosefof on 24 ינו׳
@Data
@Document
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Movie {
    @Id

    private String id;
    private String movieName;
    private int length;
    private String director;
    private Boolean recommended;
    private String img;
    private String description;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private LocalDateTime movieDate;
    private MovieTheater movieTheaters;
    private int cardAmount;

}
