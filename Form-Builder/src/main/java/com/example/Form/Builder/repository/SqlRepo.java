package com.example.Form.Builder.repository;

import com.example.Form.Builder.entities.entity.Form;
import com.example.Form.Builder.entities.entity.FormComponent;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    @Query("select component from FormComponent component ")
    List<FormComponent> findComponent();

    @Query("select component from FormComponent component ")
    List<FormComponent> findComponents();

}
