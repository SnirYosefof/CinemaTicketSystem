package com.example.CinemaTicketSystem.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

//Created by sniryosefof on 24 ינו׳
@Data
@Document
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class MovieTheater {
    @Id
    private String id;
    private TheaterName name;
    private int[][] places;

}
