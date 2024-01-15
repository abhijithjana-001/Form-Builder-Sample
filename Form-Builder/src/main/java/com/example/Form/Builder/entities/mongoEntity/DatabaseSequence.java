package com.example.Form.Builder.entities.mongoEntity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Builder
@Document(collection = "db_sequence")
public class DatabaseSequence {
    @Id
    private String id;

    private long seq;


}
