package com.example.Form.Builder.repostory.mongodb.repo;

import com.example.Form.Builder.entities.mongoEntity.MongoForm;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MongoRepo extends MongoRepository<MongoForm, String> {

    void deleteByTitle(String title);

    Optional<MongoForm> findByTitle(String title);
}