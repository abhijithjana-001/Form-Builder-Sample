package com.example.Form.Builder.service.impl;

import com.example.Form.Builder.dto.response.ResponseDto;
import com.example.Form.Builder.entities.entity.Form;
import com.example.Form.Builder.entities.entity.FormComponent;
import com.example.Form.Builder.repository.SqlRepo;
import com.example.Form.Builder.service.FormService;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Conditional(MysqlPostgreSqlCondition.class)
public class SqlFormService implements FormService {
    private final SqlRepo repo;

    public SqlFormService(SqlRepo repo) {
        this.repo = repo;
    }

    @Override
    public ResponseDto<Object> saveOrUpdateForm(Form form, String title) {
        if(title!=null)
            form.setId(repo.findByTitle(title).get().getId());
        Form save = repo.save(form);

        return new ResponseDto<>(true,"Form created or updated successfully",save);
    }

    @Override
    @Transactional
    public ResponseDto<Void> deleteFormByTitle(String title) {
        repo.deleteByTitle(title);
        return new ResponseDto<>(true,"Form deleted sql successfully",null);
    }

    @Override
    public ResponseDto<Object> getFormByTitle(String title) {
        Form form = repo.findByTitle(title).get();

       return new ResponseDto<>(true,"Successfully found",form);
    }


    public ResponseDto<List<Form>> findByTitleStartingWithLetter(String letter) {
      List<Form> form = repo.findByTitleStartingWithLetter(letter);
        return new ResponseDto<>(true,"List of forms",form);
    }

    public ResponseDto<Object> getFormsWithEmptyComponents() {
        List<Form> forms = repo.emptyFormComponent();

        return new ResponseDto<>(true,"List of forms",forms);
    }


}
