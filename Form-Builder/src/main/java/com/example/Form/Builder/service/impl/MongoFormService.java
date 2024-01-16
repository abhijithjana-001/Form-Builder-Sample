package com.example.Form.Builder.service.impl;

import com.example.Form.Builder.config.MongoFormMapStruct;
import com.example.Form.Builder.dto.response.ResponseDto;
import com.example.Form.Builder.entities.entity.Form;
import com.example.Form.Builder.entities.mongoEntity.MongodbForm;
import com.example.Form.Builder.repository.MongodbRepo;
import com.example.Form.Builder.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class MongoFormService implements FormService {


    @Autowired
    MongodbRepo mongodbRepo;

    @Autowired
    MongoFormMapStruct mongoFormMapStruct;


    public ResponseDto<Object> saveOrUpdateForm(Form form, String title) {
        if (title == null) {
            MongodbForm mongoForm = mongoFormMapStruct.toEntity(form);
            MongodbForm save= mongodbRepo.save(mongoForm);
            return new ResponseDto<>(true, "Form created successfully ", Arrays.asList());
        } else {
            MongodbForm form3 = mongodbRepo.findByTitle(title).get();
            MongodbForm mongoForm = mongoFormMapStruct.toEntity(form);
            mongoForm.set_id(form3.get_id());
            MongodbForm save3 = mongodbRepo.save(mongoForm);
            return new ResponseDto<>(true, "Form updated successfully", Arrays.asList());
        }

    }

    public ResponseDto<Void> deleteFormByTitle(String title){
        mongodbRepo.deleteByTitle(title);

        return new ResponseDto<>(true,"Form deleted successfully",null);
    }

    public ResponseDto<Object> getFormByTitle(String title){
       MongodbForm form = mongodbRepo.findByTitle(title).get();
        return new ResponseDto<>(true,"Successfully found with title",form);
    }
}
