package com.example.CinemaTicketSystem.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

//Created by sniryosefof on 03 מרץ
    @Data
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    @RedisHash("Movies")
    public class Recommended implements Serializable {
//    private static final long serialVersionUID = -8048026196529138554L;


    @Id
        private int id;
        private String movieName;
        private int length;
        private String director;
        private String img;
        @Enumerated(EnumType.STRING)
        private Genre genre;
    }

