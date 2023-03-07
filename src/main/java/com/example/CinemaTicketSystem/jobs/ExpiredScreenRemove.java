package com.example.CinemaTicketSystem.jobs;

import com.example.CinemaTicketSystem.rep.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

//Created by sniryosefof on 28 פבר׳
@Component
@RequiredArgsConstructor
public class ExpiredScreenRemove {

    private final MovieRepository movieRepository;
    private final int day=1000*60*60*24;

    @Scheduled(fixedRate = day)
    public void job(){
        movieRepository.deleteByMovieDateLessThan(LocalDateTime.now());
    }
}
