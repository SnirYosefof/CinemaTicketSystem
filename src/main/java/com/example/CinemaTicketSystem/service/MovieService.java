package com.example.CinemaTicketSystem.service;

import com.example.CinemaTicketSystem.Exception.SysException;
import com.example.CinemaTicketSystem.beans.*;

import javax.transaction.SystemException;
import java.util.List;

//Created by sniryosefof on 18 פבר׳
public interface MovieService {

    Movie addMovie(Movie movie, TheaterName name) throws SystemException, SysException;

    Movie updateMovie(Movie movie) throws SystemException, SysException;

    void deleteMovie(String movieName) throws SysException;

    List<Movie> getByGenre(Genre genre);

    List<Movie> findAllMovies();

    List<Movie> getCinemaMovie();

    List<Recommended> findAllRecommended();

    void deleteRecommended(int id);
}
