package com.example.Form.Builder.repository;

import com.example.Form.Builder.entities.mongoEntity.MongodbForm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MongodbRepo extends MongoRepository<MongodbForm, String> {

    void deleteByTitle(String title);
    Optional<MongodbForm> findByTitle(String title);
}