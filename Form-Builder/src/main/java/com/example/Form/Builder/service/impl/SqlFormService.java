package com.example.Form.Builder.service.impl;

import com.example.Form.Builder.dto.response.ResponseDto;
import com.example.Form.Builder.entities.entity.Form;
import com.example.Form.Builder.repostory.SqlRepo;
import com.example.Form.Builder.service.FormService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service

public class SqlFormService implements FormService {

    @Autowired
    private SqlRepo repo;

    @Override
    public ResponseDto<Object> saveOrUpdateForm(Form form, String title) {
        if(title!=null) {
            form.setId(repo.findByTitle(title).get().getId());
            System.out.println( repo.findByTitle(title).get());
        }
        Form save = repo.save(form);
        return new ResponseDto<>(true,"Form created or updated successfully",Arrays.asList(save));
    }

    @Override
    @Transactional
    public ResponseDto<Void> deleteFormByTitle(String title) {
        repo.deleteByTitle(title);
        return new ResponseDto<>(true,"Form deleted successfully",null);
    }

    @Override
    public ResponseDto<Object> getFormByTitle(String title) {
        Form form = repo.findByTitle(title).get();
       return new ResponseDto<>(true,"Successfully found with title",form);
    }
}
