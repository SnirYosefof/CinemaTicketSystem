package com.example.CinemaTicketSystem.rep;

import com.example.CinemaTicketSystem.beans.Genre;
import com.example.CinemaTicketSystem.beans.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

//Created by sniryosefof on 27 ינו׳
public interface MovieRepository extends MongoRepository<Movie, String> {

    List<Movie> findByGenre(Genre genre);

    boolean existsByMovieName(String name);

    boolean existsByDirector(String director);

    void deleteByMovieName(String movieName);

    void deleteByMovieDateLessThan(LocalDateTime date);


}
