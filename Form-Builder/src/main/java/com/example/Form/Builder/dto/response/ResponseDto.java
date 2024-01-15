package com.example.Form.Builder.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseDto<T> {
    private  Boolean success;
    private  String message;

    private  T result;
}
