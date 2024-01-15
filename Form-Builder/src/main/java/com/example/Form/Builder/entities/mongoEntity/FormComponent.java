package com.example.Form.Builder.entities.mongoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormComponent {



    private String label;
    private String type;
}