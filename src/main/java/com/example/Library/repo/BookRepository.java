package com.example.Library.repo;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.example.Library.Constants.BEAN_LIBRARY_CACHE;

@Repository
public class BookRepository {

    @Autowired
    @Qualifier(BEAN_LIBRARY_CACHE)
    RedisTemplate<String, String> redisTemplate;

    public void save(String id, String objectString){
        redisTemplate.opsForValue().set(id, objectString);
    }

    public Optional<String> getBook(String id){
        return Optional.ofNullable(redisTemplate.opsForValue().get(id));
    }
}
