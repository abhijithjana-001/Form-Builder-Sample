package com.example.Form.Builder.repository;

import com.example.Form.Builder.entities.entity.Form;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SqlRepo extends JpaRepository<Form,Long>  {

    @CacheEvict(value = "title", key = "#title")
    void  deleteByTitle(String title);

    @Cacheable(value = "gettitles",key = "#title")
    Optional<Form> findByTitle(String title);
}
