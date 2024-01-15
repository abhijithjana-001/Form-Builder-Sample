package com.example.Form.Builder.config;

import com.example.Form.Builder.dto.request.FormDto;

import com.example.Form.Builder.entities.entity.Form;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FormMapStruct {
   @Mapping(target = "id" , ignore = true)
   Form toEntity(FormDto formDto);
}
