package com.example.CinemaTicketSystem.service;

import com.example.CinemaTicketSystem.Exception.ErrMsg;
import com.example.CinemaTicketSystem.Exception.SysException;
import com.example.CinemaTicketSystem.beans.Client;
import com.example.CinemaTicketSystem.beans.Movie;
import com.example.CinemaTicketSystem.rep.ClientRepository;
import com.example.CinemaTicketSystem.rep.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

//Created by sniryosefof on 20 פבר׳
@Service
@RequiredArgsConstructor
public class ClientImpl implements ClientService{
    private final MovieRepository movieRepository;
    private final ClientRepository clientRepository;
    @Override
    public UUID purchaseScreen(int clientId, int row, int col, Movie movie) throws SysException {
        clientRepository.findById(clientId).orElseThrow(()-> new SysException(ErrMsg.ID_DONT_EXIST));
        if (!LocalDateTime.now().isBefore(movie.getMovieDate())){
            throw new SysException(ErrMsg.YOU_CANT_BUY_PAST_SCREEN);
        }
        if (movie.getCardAmount()<=0){
            throw new SysException(ErrMsg.THE_CARD_IS_OVER);
        }
        System.out.println(movie.getMovieTheaters().getPlaces()[row][col]);
        if (movie.getMovieTheaters().getPlaces()[row][col]==0){
            throw new SysException(ErrMsg.THE_PLACE_IS_TAKEN);
        }
        movie.setCardAmount(movie.getCardAmount()-1);
        int[][]arr= movie.getMovieTheaters().getPlaces();
        arr[row][col]=0;
        movie.getMovieTheaters().setPlaces(arr);
        movieRepository.save(movie);
        System.out.println(movie.getMovieTheaters().getPlaces()[row][col]);
        return UUID.randomUUID();
    }

    public List<Client> getAllClient(){
        return clientRepository.findAll();
    }

    public Client findClientByName(String name) throws SysException {
        if (!clientRepository.existsByName(name)){
            throw new SysException(ErrMsg.NAME_DONT_EXIST);
        }
        return clientRepository.findByName(name);
    }

    public void deleteClient(int id) throws SysException {
        clientRepository.findById(id).orElseThrow(()->new SysException(ErrMsg.ID_DONT_EXIST));
        clientRepository.deleteById(id);
    }
}
