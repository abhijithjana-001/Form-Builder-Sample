package com.example.Form.Builder.service.impl;


import com.example.Form.Builder.config.MongodbFormMapStruct;
import com.example.Form.Builder.dto.response.ResponseDto;
import com.example.Form.Builder.entities.entity.Form;
import com.example.Form.Builder.entities.mongoEntity.MongodbForm;
import com.example.Form.Builder.repository.MongodbRepo;
import com.example.Form.Builder.service.FormService;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Conditional(MongoRedisCondition.class)
public class MongoFormService implements FormService {
    private final MongodbRepo mongodbRepo;
    private final MongodbFormMapStruct mongodbFormMapStruct;

    public MongoFormService(MongodbRepo mongodbRepo, MongodbFormMapStruct mongodbFormMapStruct) {
        this.mongodbRepo = mongodbRepo;
        this.mongodbFormMapStruct = mongodbFormMapStruct;
    }

    public ResponseDto<Object> saveOrUpdateForm(Form form, String title) {
        if (title == null) {
            MongodbForm mongodbForm = mongodbFormMapStruct.toEntity(form);
            MongodbForm save= mongodbRepo.save(mongodbForm);
            return new ResponseDto<>(true, "Form created successfully", save);
        } else {
            MongodbForm form3 = (MongodbForm) mongodbRepo.findByTitle(title).get();
            MongodbForm mongodbForm = mongodbFormMapStruct.toEntity(form);
            mongodbForm.set_id(form3.get_id());
            MongodbForm save3 = mongodbRepo.save(mongodbForm);
            return new ResponseDto<>(true, "Form updated successfully", save3);
        }

    }

    public ResponseDto<Void> deleteFormByTitle(String title){
        mongodbRepo.deleteByTitle(title);

        return new ResponseDto<>(true,"Form deleted successfully",null);
    }

    public ResponseDto<Object> getFormByTitle(String title){
        MongodbForm form = (MongodbForm) mongodbRepo.findByTitle(title).get();
        return new ResponseDto<>(true,"Successfully found",form);
    }

    @Override
    public ResponseDto<List<Form>> findByTitleStartingWithLetter(String letter){
        List<Form> forms = mongodbRepo.findByMongoFormStartingWithLetter(letter);
        return new ResponseDto<>(true,"Successfully found",forms);
    }
    @Override
    public ResponseDto<Object> getFormsWithEmptyComponents() {

        List<MongodbForm> formsWithEmptyComponents = mongodbRepo.findFormsWithEmptyComponents();
        return new ResponseDto<>(true,"Successfully found",formsWithEmptyComponents);
    }




}

