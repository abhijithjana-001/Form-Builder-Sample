//package com.example.Form.Builder.service.impl;
//
//import com.example.Form.Builder.config.MongoFormMapStruct;
//import com.example.Form.Builder.dto.response.ResponseDto;
//
//import com.example.Form.Builder.entities.entity.Form;
//
//import com.example.Form.Builder.repostory.SqlRepo;
//import com.example.Form.Builder.repostory.mongodb.repo.MongodbRepo;
//
//import com.example.Form.Builder.service.FormService;
//import lombok.val;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Service
//public class FormServiceImpl implements FormService {
//    @Value("${current.database}")
//    private String dbName;
//
//
//
//
//
//
//    public ResponseDto<List<Object>> saveOrUpdateForm(Form form, String title){
//
//
//      if(title==null){
//
//
//          return new ResponseDto<>(true,"Form created successfully ", Arrays.asList( ));
//      }
//      else{
////          Form form =
////          form.setId(form1.getId());
////          Form save = formRepo.save(form);
////
////          Form form2 = postgreRepo.findByTitle(title).get();
////          form.setId(form2.getId());
////          Form save2= postgreRepo.save(form);
//
////          MongoForm form3 = mongodbRepo.findByTitle(title).get();
////          MongoForm mongoForm=mongoFormMapStruct.toEntity(form);
////          mongoForm.set_id(form3.get_id());
////         MongoForm save3= mongodbRepo.save(mongoForm);
//
//
//          return new ResponseDto<>(true,"Form updated successfully",Arrays.asList());
//      }
//    }
//
//
//    public ResponseDto<Form> deleteFormByTitle(String title){
////        Form form1 = formRepo.findByTitle(title).get();
////      formRepo.deleteById(form1.getId());
////        Form form2 = postgreRepo.findByTitle(title).get();
////        postgreRepo.deleteById(form2.getId());
////        mongodbRepo.deleteByTitle(title);
//
//        return new ResponseDto<>(true,"Form deleted successfully",null);
//    }
//
//    public ResponseDto<Form> getFormByTitle(String title){
////        Form form = formRepo.findByTitle(title).get();
//        return new ResponseDto<>(true,"Successfully found with title",null);
//    }
//}
