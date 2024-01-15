package com.example.Form.Builder.entities.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "form",
        uniqueConstraints = @UniqueConstraint(columnNames = "title")
)
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String title;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "form_id")
    private List<FormComponent> components;


}
