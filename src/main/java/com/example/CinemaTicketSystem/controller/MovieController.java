package com.example.CinemaTicketSystem.controller;

import com.example.CinemaTicketSystem.Exception.ErrMsg;
import com.example.CinemaTicketSystem.Exception.SecException;
import com.example.CinemaTicketSystem.Exception.SecMsg;
import com.example.CinemaTicketSystem.Exception.SysException;
import com.example.CinemaTicketSystem.beans.*;
import com.example.CinemaTicketSystem.rep.ClientRepository;
import com.example.CinemaTicketSystem.security.TokenManger;
import com.example.CinemaTicketSystem.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.SystemException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

//Created by sniryosefof on 22 פבר׳
@RestController
@AllArgsConstructor
@RequestMapping("/api/movie")
public class MovieController {

    private final MovieService movieService;
    private final TokenManger tokenManger;
    private final ClientRepository clientRepository;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void addScreen(@RequestHeader("Authorization") UUID token, @RequestBody Movie movie) throws SysException, SystemException, SecException {
        Client client = clientRepository.findById(tokenManger.getUserId(token)).orElseThrow(() -> new SysException(ErrMsg.ID_DONT_EXIST));
        if (client.getClientType() != ClientType.Admin) {
            throw new SecException(SecMsg.TYPE_UNCORRECTED);
        }
        TheaterName theaterName = movie.getMovieTheaters().getName();
        movieService.addMovie(movie, theaterName);
    }

    @PutMapping("/{movieId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editScreen(@RequestHeader("Authorization") UUID token, @RequestParam String movieId, @RequestBody Movie movie) throws SysException, SystemException, SecException {
        movie.setId(movieId);
        Client client = clientRepository.findById(tokenManger.getUserId(token)).orElseThrow(() -> new SysException(ErrMsg.ID_DONT_EXIST));
        if (client.getClientType() != ClientType.Admin) {
            throw new SecException(SecMsg.TYPE_UNCORRECTED);
        }
        movieService.updateMovie(movie);
    }

    @DeleteMapping("/{movieId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteScreen(@RequestHeader("Authorization") UUID token, @RequestParam String movieId) throws SecException, SysException {
        Client client = clientRepository.findById(tokenManger.getUserId(token)).orElseThrow(() -> new SysException(ErrMsg.ID_DONT_EXIST));
        if (client.getClientType() != ClientType.Admin) {
            throw new SecException(SecMsg.TYPE_UNCORRECTED);
        }
        movieService.deleteMovie(movieId);

    }

    @GetMapping()
    public List<Movie> findAllScreen() {
        return movieService.findAllMovies();
    }

    @GetMapping("/MovieGenre")
    public List<Movie> findByGenres(@RequestParam Genre genre) {
        return movieService.getByGenre(genre);
    }

    @GetMapping("/cinemaMovie")
    public List<Movie> getAllCinemaMovie(){
        return movieService.getCinemaMovie();
    }
    @GetMapping("/recommendedMovies")
    public List<Recommended> getAllRecommendedMovies(){
        return movieService.findAllRecommended();
    }
    @DeleteMapping("/deleteRecommended/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  deleteRecommendedMovie(int id){
         movieService.deleteRecommended(id);
    }

}
