package com.example.CinemaTicketSystem.rep;

import com.example.CinemaTicketSystem.beans.Recommended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepositoryRedis {

    public static final String HASH_KEY = "Movies";
    @Autowired
    private RedisTemplate template;

    public Recommended save(Recommended movie){
        template.opsForHash().put(HASH_KEY,movie.getId(),movie);
        return movie;
    }

    public List<Recommended> findAll(){
        return template.opsForHash().values(HASH_KEY);
    }

    public Recommended findRecommendedById(int id){
        return (Recommended) template.opsForHash().get(HASH_KEY,id);
    }



    public String deleteProduct(int id){
        template.opsForHash().delete(HASH_KEY,id);
        return "Recommended movie removed !!";
    }
}