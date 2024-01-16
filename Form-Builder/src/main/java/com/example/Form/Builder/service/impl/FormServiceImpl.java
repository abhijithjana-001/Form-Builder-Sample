package com.example.Form.Builder.service.impl;

import com.example.Form.Builder.config.MongoFormMapStruct;
import com.example.Form.Builder.dto.response.ResponseDto;

import com.example.Form.Builder.entities.entity.Form;
import com.example.Form.Builder.entities.mongoEntity.MongoForm;

import com.example.Form.Builder.repostory.mongodb.repo.MongoRepo;
import com.example.Form.Builder.repostory.mysql.repo.FormRepo;
import com.example.Form.Builder.repostory.postgres.repo.PostgreRepo;
import com.example.Form.Builder.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FormServiceImpl implements FormService {
    @Autowired
    private FormRepo formRepo;
    @Autowired
    private PostgreRepo postgreRepo;

    @Autowired
    private MongoRepo mongoRepo;

    @Autowired
    private MongoFormMapStruct mongoFormMapStruct;

    public ResponseDto<List<Object>> saveOrUpdateForm(Form form, String title){
      if(title==null){
          Form save = formRepo.save(form);
          Form save1 = postgreRepo.save(form);
          MongoForm mongoForm=mongoFormMapStruct.toEntity(form);
          MongoForm save3 = mongoRepo.save(mongoForm);
          return new ResponseDto<>(true,"Form created successfully", Arrays.asList(save,save1, save3));
      }

      else{
          Form form1 = formRepo.findByTitle(title).get();
          form.setId(form1.getId());
          Form save = formRepo.save(form);

          Form form2 = postgreRepo.findByTitle(title).get();
          form.setId(form2.getId());
          Form save2= postgreRepo.save(form);

          MongoForm form3 = mongoRepo.findByTitle(title).get();
          MongoForm mongoForm=mongoFormMapStruct.toEntity(form);
          mongoForm.set_id(form3.get_id());
         MongoForm save3= mongoRepo.save(mongoForm);


          return new ResponseDto<>(true,"Form updated successfully",Arrays.asList(save,save2,save3));
      }
    }


    public ResponseDto<Form> deleteFormByTitle(String title){
        Form form1 = formRepo.findByTitle(title).get();
      formRepo.deleteById(form1.getId());
        Form form2 = postgreRepo.findByTitle(title).get();
        postgreRepo.deleteById(form2.getId());
        mongoRepo.deleteByTitle(title);

        return new ResponseDto<>(true,"Form deleted successfully",null);
    }

    public ResponseDto<Form> getFormByTitle(String title){
        Form form = formRepo.findByTitle(title).get();
        return new ResponseDto<>(true,"Successfully found with title",form);
    }
}
