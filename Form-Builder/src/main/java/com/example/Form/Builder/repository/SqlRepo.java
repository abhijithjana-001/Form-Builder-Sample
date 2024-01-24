package com.example.Form.Builder.repository;

import com.example.Form.Builder.entities.entity.Form;
import com.example.Form.Builder.entities.entity.FormComponent;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SqlRepo extends JpaRepository<Form,Long>  {
    void  deleteByTitle( String title);
    Optional<Form> findByTitle( String title);

    @Query("select f from Form f where f.title LIKE :letter%")
    List<Form> findByTitleStartingWithLetter(@Param("letter") String letter );

    @Query("Select f from Form f where size(f.components) =0")
    List<Form> emptyFormComponent();
}
