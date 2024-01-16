package com.example.Form.Builder.repostory.mongodb.repo;

import com.example.Form.Builder.entities.mongoEntity.MongoForm;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MongoRepo extends MongoRepository<MongoForm, String> {

    void deleteByTitle(String title);

    Optional<MongoForm> findByTitle(String title);
}