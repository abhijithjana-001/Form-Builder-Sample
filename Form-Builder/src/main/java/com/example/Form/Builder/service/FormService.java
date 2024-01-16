package com.example.Form.Builder.service;

import com.example.Form.Builder.dto.response.ResponseDto;
import com.example.Form.Builder.entities.entity.Form;

import java.util.List;

public interface FormService {
    public ResponseDto<Object> saveOrUpdateForm(Form form, String title);
    public ResponseDto<Void> deleteFormByTitle(String title);
    public ResponseDto<Object> getFormByTitle(String title);

}
