package com.example.Form.Builder.entities.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ComponentValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ValuesId;
    private String value;

}
