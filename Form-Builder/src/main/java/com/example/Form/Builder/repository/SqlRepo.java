package com.example.Form.Builder.repository;

import com.example.Form.Builder.entities.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SqlRepo extends JpaRepository<Form,Long>  {
    void  deleteByTitle(String title);


    Optional<Form> findByTitle(String title);
}
