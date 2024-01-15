package com.example.Form.Builder.repostory.mysql.repo;


import com.example.Form.Builder.entities.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FormRepo extends JpaRepository<Form,Long> {


    void  deleteByTitle(String title);


    Optional<Form> findByTitle(String title);
}
