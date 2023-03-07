package com.example.CinemaTicketSystem.rep;

import com.example.CinemaTicketSystem.beans.Client;
import com.example.CinemaTicketSystem.beans.ClientType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//Created by sniryosefof on 27 ינו׳
public interface ClientRepository extends JpaRepository<Client,Integer> {

    boolean existsByEmail(String email);
    boolean existsByName(String name);
    Client findTop1ByEmail(String email);
    Client findByName(String name);
    boolean existsByEmailAndPassword(String email,String password);
    @Query(value = "SELECT (name) FROM Cinema.client where email=?;", nativeQuery = true)
    String findNameByEmail(String email);
    @Query(value = "SELECT (client_type) FROM Cinema.client where email=?;", nativeQuery = true)
    ClientType findTypeByEmail(String email);


}
