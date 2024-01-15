package com.example.Form.Builder.entities.mongoEntity;

import org.springframework.data.annotation.Id;

public class Values {
    @Id
    private String valueId;
    private String value;
}
