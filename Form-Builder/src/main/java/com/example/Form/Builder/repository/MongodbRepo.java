package com.example.Form.Builder.repository;

import com.example.Form.Builder.entities.mongoEntity.MongodbForm;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface MongodbRepo extends MongoRepository<MongodbForm, String> {

   void deleteByTitle(String title);
    @Cacheable(value = "myCache", key = "#title")
    Optional<List<MongodbForm>> findByTitle(String title);

    @Cacheable(value = "myCache", key = "#_id")
    Optional<MongodbForm> findById(String id);
}