package com.example.Form.Builder.repository;

import com.example.Form.Builder.entities.entity.Form;
import com.example.Form.Builder.entities.mongoEntity.MongodbForm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MongodbRepo extends MongoRepository<MongodbForm, String> {
    void deleteByTitle(String title);

    Optional<MongodbForm> findByTitle(String title);

    @Query("{ 'components': {'$exists': true, '$size': 0  } }")
    List<MongodbForm> findFormsWithEmptyComponents();

    @Query("{ 'title' : { $regex: ?0 ,$options: 'i' } }")
    List<Form> findByMongoFormStartingWithLetter(String letter);


}