package com.example.Form.Builder.entities.mongoEntity;

import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "form" )
public class MongodbForm implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";

    @Id
    private String _id;

    @Indexed(unique = true,background = true)
    private String title;


    private List<FormComponent> components;
}
