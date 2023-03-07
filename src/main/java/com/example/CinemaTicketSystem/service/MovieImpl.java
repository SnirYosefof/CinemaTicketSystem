package com.example.CinemaTicketSystem.service;

import com.example.CinemaTicketSystem.Exception.ErrMsg;
import com.example.CinemaTicketSystem.Exception.SysException;
import com.example.CinemaTicketSystem.beans.*;
import com.example.CinemaTicketSystem.rep.MovieRepository;
import com.example.CinemaTicketSystem.rep.MovieRepositoryRedis;
import com.example.CinemaTicketSystem.rep.MovieTheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

//Created by sniryosefof on 18 פבר׳
@Service
@RequiredArgsConstructor
public class MovieImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieRepositoryRedis movieRepositoryRedis;
    private final MovieTheaterRepository movieTheaterRepository;

    @Override
    public Movie addMovie(Movie movie,TheaterName name) throws SysException {
        int count=0;
        if (!LocalDateTime.now().isBefore(movie.getMovieDate())){
            throw new SysException(ErrMsg.FUTURE_DATE);
        }
        movie.setMovieTheaters(movieTheaterRepository.findByName(name));
        if (movie.getRecommended()) {
            if (!movieRepository.existsByMovieName(movie.getMovieName())) {
                Recommended recommended= Recommended.builder()
                        .id((int) (Math.random()*Integer.MAX_VALUE))
                        .movieName(movie.getMovieName())
                        .director(movie.getDirector())
                        .genre(movie.getGenre())
                        .length(movie.getLength())
                        .img(movie.getImg())
                        .build();
                movieRepositoryRedis.save(recommended);
            } else {
                throw new SysException(ErrMsg.MOVIE_RECOMMENDED);
            }
        }
        for (int i = 0; i < movie.getMovieTheaters().getPlaces().length; i++) {
            for (int j = 0; j < movie.getMovieTheaters().getPlaces()[i].length; j++) {
                int[][] arr = movie.getMovieTheaters().getPlaces();
                arr[i][j] = 1;
                count++;
                movie.getMovieTheaters().setPlaces(arr);
            }
        }
        movie.setCardAmount(count);
        return movieRepository.insert(movie);
    }

    @Override
    public Movie updateMovie( Movie movie) throws SysException {
        if (!LocalDateTime.now().isBefore(movie.getMovieDate())){
            throw new SysException(ErrMsg.FUTURE_DATE);
        }
        if (!movieRepository.existsById(movie.getId())) {
            throw new SysException(ErrMsg.ID_DONT_EXIST);
        }
        if (!movieRepository.existsByMovieName(movie.getMovieName())) {
            throw new SysException(ErrMsg.CANT_UPDATE_MOVIE_NAME);
        }
        if (!movieRepository.existsByDirector(movie.getDirector())) {
            throw new SysException(ErrMsg.CANT_UPDATE_MOVIE_DIRECTOR);
        }
        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(String movieId) throws SysException {
    if (!movieRepository.existsById(movieId)){
        throw new SysException(ErrMsg.ID_DONT_EXIST);
    }
    movieRepository.deleteById(movieId);
    }



    @Override
    public List<Movie> getByGenre(Genre genre) {
        return movieRepository.findByGenre(genre);
    }

    @Override
    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> getCinemaMovie() {
        List<Movie> movieList=new ArrayList<>();
        List<Movie> allScreen = movieRepository.findAll();
        movieList.add(allScreen.get(0));
        List<String> uniqueMovieNames = new ArrayList<>();
        for (Movie movie : movieList) {
            uniqueMovieNames.add(movie.getMovieName());
        }
        for (Movie movie : allScreen) {
            if (!uniqueMovieNames.contains(movie.getMovieName())) {
                movieList.add(movie);
                uniqueMovieNames.add(movie.getMovieName());
            }
        }

        return movieList;
    }

    @Override
    public List<Recommended> findAllRecommended() {
        return movieRepositoryRedis.findAll() ;
    }

    @Override
    public void deleteRecommended(int id) {
        movieRepositoryRedis.deleteProduct(id);
    }
}
