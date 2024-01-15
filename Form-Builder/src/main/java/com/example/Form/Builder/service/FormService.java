package com.example.Form.Builder.service;

import com.example.Form.Builder.dto.response.ResponseDto;
import com.example.Form.Builder.entities.entity.Form;

import java.util.List;

public interface FormService {
    public ResponseDto<List<Object>> saveOrUpdateForm(Form form, String title);
    public ResponseDto<Form> deleteFormByTitle(String title);
    public ResponseDto<Form> getFormByTitle(String title);

}
