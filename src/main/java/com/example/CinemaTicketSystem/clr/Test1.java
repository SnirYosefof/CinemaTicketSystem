package com.example.CinemaTicketSystem.clr;

import com.example.CinemaTicketSystem.beans.*;
import com.example.CinemaTicketSystem.rep.ClientRepository;
import com.example.CinemaTicketSystem.rep.MovieRepository;
import com.example.CinemaTicketSystem.rep.MovieRepositoryRedis;
import com.example.CinemaTicketSystem.rep.MovieTheaterRepository;
import com.example.CinemaTicketSystem.service.ClientService;
import com.example.CinemaTicketSystem.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

//Created by sniryosefof on 27 ינו׳
@Component
@Order(1)
@RequiredArgsConstructor
public class Test1 implements CommandLineRunner {
    private final MovieService movieService;
    private final ClientRepository clientRepository;
    private final MovieRepository movieRepository;
    private final MovieTheaterRepository movieTheaterRepository;
    private final MovieRepositoryRedis movieRepositoryRedis;
    private final ClientService clientService;

    @Override
    public void run(String... args) throws Exception {

        Client client1 = Client.builder()
                .name("snir")
                .email("snir@gmail.com")
                .password("qwe123")
                .clientType(ClientType.Admin)
                .build();
     Client client2 = Client.builder()
                .name("yasmin")
                .email("yasmin@gmail.com")
                .password("123123")
                .clientType(ClientType.Client)
                .build();

        Movie movie1 = Movie.builder()
                .movieDate(LocalDateTime.now().plusMonths(3).plusHours(4))
                .movieName("david")
                .description("123")
                .genre(Genre.Action)
                .img("moshe")
                .cardAmount(82)
                .recommended(false)
                .length(12345).build();


        MovieTheater movieTheater = MovieTheater.builder()

                .places(new int[2][2])
                .name(TheaterName.chaplin)
                .build();
        Movie movie2 = Movie.builder()
                .movieDate(LocalDateTime.now().plusMonths(3).plusHours(4))
                .movieName("moshe")
                .description("123")
                .genre(Genre.Action)
                .recommended(false)
                .img("mosheAndDavid")
                .movieTheaters(movieTheater)
                .length(12345).build();

//        movieRepository.insert(movie1);
//        movieRepository.insert(movie2);
        clientRepository.save(client1);
        clientRepository.save(client2);
        //duplicate
//        movieTheaterRepository.save(movieTheater);
////        System.out.println(movieRepository.findByGenre(Genre.Action));
//        System.out.println(movieRepository.existsByMoveName("david"));
//        movie1.setMoveName("david2");
//        System.out.println(movie1);
        movieService.addMovie(movie2,TheaterName.chaplin);
        movieService.addMovie(movie1,TheaterName.chaplin);
        System.out.println("@@@@@@@@@@@@");
//        clientService.purchaseScreen(1,1,movie1);
        movie1.setCardAmount(1919);
//        movieService.updateMovie(movie1.getId(),movie1);
//        movieRepositoryRedis.findAll().forEach(System.out::println);
//        movieService.deleteRecommended(0);
        System.out.println(movieService.findAllRecommended());
        System.out.println("@@@@@@@@@@@@");
        System.out.println(movieService.findAllMovies());
        System.out.println(clientRepository.findNameByEmail(client1.getEmail()));
        System.out.println(clientRepository.findTypeByEmail(client1.getEmail()));
    }
}
