package com.example.Form.Builder.service.impl;

import com.example.Form.Builder.dto.response.ResponseDto;
import com.example.Form.Builder.entities.entity.Form;
import com.example.Form.Builder.repostory.SqlRepo;
import com.example.Form.Builder.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service

public class SqlFormService implements FormService {

    @Autowired
    private SqlRepo repo;

    @Override
    public ResponseDto<List<Object>> saveOrUpdateForm(Form form, String title) {

        if(title!=null){
          form.setId(repo.findByTitle(title).get().getId());


        }
        Form save = repo.save(form);

        return new ResponseDto<>(true,"Form updated successfully",Arrays.asList(save));

    }

    @Override
    public ResponseDto<Form> deleteFormByTitle(String title) {
        return null;
    }

    @Override
    public ResponseDto<Form> getFormByTitle(String title) {
        return null;
    }
}
