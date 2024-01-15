package com.example.Form.Builder.config;

import com.example.Form.Builder.entities.entity.Form;
import com.example.Form.Builder.entities.mongoEntity.MongoForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MongoFormMapStruct {
    MongoForm toEntity(Form form);
}
