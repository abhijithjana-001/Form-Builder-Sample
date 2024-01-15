package com.example.Form.Builder.dto.request;

import com.example.Form.Builder.entities.entity.FormComponent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FormDto {
    private String title;
    private List<FormComponent> components;
}
