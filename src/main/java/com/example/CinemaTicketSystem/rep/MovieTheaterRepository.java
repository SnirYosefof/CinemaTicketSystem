package com.example.CinemaTicketSystem.rep;

import com.example.CinemaTicketSystem.beans.MovieTheater;
import com.example.CinemaTicketSystem.beans.TheaterName;
import org.springframework.data.mongodb.repository.MongoRepository;

//Created by sniryosefof on 27 ינו׳
public interface MovieTheaterRepository extends MongoRepository<MovieTheater,String> {

    MovieTheater findByName(TheaterName name);

}
