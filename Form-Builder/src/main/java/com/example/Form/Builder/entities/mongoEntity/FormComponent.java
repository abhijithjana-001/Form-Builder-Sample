package com.example.Form.Builder.entities.mongoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormComponent  implements Serializable {



    private String label;
    private String type;
}