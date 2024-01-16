package com.example.Form.Builder.service.impl;

import com.example.Form.Builder.config.MongoFormMapStruct;
import com.example.Form.Builder.dto.response.ResponseDto;
import com.example.Form.Builder.entities.entity.Form;
import com.example.Form.Builder.entities.mongoEntity.MongoForm;
import com.example.Form.Builder.repostory.mongodb.repo.MongoRepo;
import com.example.Form.Builder.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class MongoFormService implements FormService {


    @Autowired
    MongoRepo mongoRepo;

    @Autowired
    MongoFormMapStruct mongoFormMapStruct;

    @Value("${current.database}")
    private String dbName;

    public ResponseDto<Object> saveOrUpdateForm(Form form, String title) {
        if (title == null) {
            MongoForm mongoForm = mongoFormMapStruct.toEntity(form);
            MongoForm save= mongoRepo.save(mongoForm);
            return new ResponseDto<>(true, "Form created successfully ", Arrays.asList());
        } else {
            MongoForm form3 = mongoRepo.findByTitle(title).get();
            MongoForm mongoForm = mongoFormMapStruct.toEntity(form);
            mongoForm.set_id(form3.get_id());
            MongoForm save3 = mongoRepo.save(mongoForm);
            return new ResponseDto<>(true, "Form updated successfully", Arrays.asList());
        }

    }

    public ResponseDto<Void> deleteFormByTitle(String title){
        mongoRepo.deleteByTitle(title);

        return new ResponseDto<>(true,"Form deleted successfully",null);
    }

    public ResponseDto<Object> getFormByTitle(String title){
       MongoForm form = mongoRepo.findByTitle(title).get();
        return new ResponseDto<>(true,"Successfully found with title",form);
    }
}
