package com.example.Form.Builder.controller;

import com.example.Form.Builder.dto.request.FormDto;
import com.example.Form.Builder.dto.response.ResponseDto;
import com.example.Form.Builder.entities.entity.Form;
import com.example.Form.Builder.entities.entity.FormComponent;
import com.example.Form.Builder.entities.mongoEntity.MongoForm;
import com.example.Form.Builder.repostory.mongodb.repo.MongoRepo;
import com.example.Form.Builder.repostory.mysql.repo.FormRepo;
import com.example.Form.Builder.repostory.postgres.repo.PostgreRepo;
import com.example.Form.Builder.service.FormService;
import com.example.Form.Builder.service.impl.FormServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FormControllerTest {
    @SpyBean
    private FormService formService;

    @SpyBean
    private  FormController formController;

    @SpyBean
    private FormRepo formRepo;

    @SpyBean
    private PostgreRepo postgreRepo;

    @SpyBean
    private MongoRepo mongoRepo;

    @Test
    void testAddFormCreate() {
        FormComponent formComponent=new FormComponent();
        formComponent.setType("test box");
        formComponent.setLabel("Enter your name");
        FormDto formDto=new FormDto(
                "Registration form",
                Arrays.asList(
                        formComponent
                ));


       doReturn(new Form()).when(formRepo).save(any(Form.class));
        doReturn(new Form()).when(postgreRepo).save(any(Form.class));
      doReturn(new MongoForm()).when(mongoRepo).save(any(MongoForm.class));
        // Act

        ResponseEntity<ResponseDto<List<Object>>> form = formController.createForm(formDto);

        //        assert
        assertEquals(HttpStatusCode.valueOf(200),form.getStatusCode());
        assertEquals("Form created successfully", Objects.requireNonNull(form.getBody()).getMessage());
        assertTrue(form.getBody().getSuccess());
    }

    @Test
    void testAddFormUpdate() {
        FormComponent formComponent=new FormComponent();
        formComponent.setType("test box");
        formComponent.setLabel("Enter your name");
        FormDto formDto=new FormDto(
                "Registration form",
                Arrays.asList(
                        formComponent
                ));


        doReturn(new Form()).when(formRepo).save(any(Form.class));
        doReturn(new Form()).when(postgreRepo).save(any(Form.class));
        doReturn(new MongoForm()).when(mongoRepo).save(any(MongoForm.class));

        doReturn(Optional.of(new Form())).when(formRepo).findByTitle(anyString());

        doReturn(Optional.of(new Form())).when(postgreRepo).findByTitle(anyString());

        doReturn(Optional.of(new MongoForm())).when(mongoRepo).findByTitle(anyString());


        // Act

        ResponseEntity<ResponseDto<List<Object>>> form = formController.updateForm(formDto,"Form updated successfully");

        //        assert
        assertEquals(HttpStatusCode.valueOf(200),form.getStatusCode());
        assertEquals("Form updated successfully", Objects.requireNonNull(form.getBody()).getMessage());
        assertTrue(form.getBody().getSuccess());
    }

    @Test
    void testGetForm(){
        doReturn(Optional.of(new Form())).when(formRepo).findByTitle(anyString());

//        act
        ResponseEntity<ResponseDto<Form>> formByTitle = formController.getFormByTitle(anyString());
//        assert

        //        assert
        assertEquals(HttpStatusCode.valueOf(200),formByTitle.getStatusCode());
        assertEquals("Successfully found with title", Objects.requireNonNull(formByTitle.getBody()).getMessage());
        assertTrue(formByTitle.getBody().getSuccess());


    }


    @Test
    void deleteByTitle(){
//        arrange
doReturn(new ResponseDto<Form>(true,"deleted successfully",null)).when(formService).deleteFormByTitle(anyString());
//        act
        ResponseEntity<ResponseDto<Form>> responseDtoResponseEntity = formController.deleteFormByTitle(anyString());

//        assert
        assertEquals(HttpStatusCode.valueOf(200),responseDtoResponseEntity.getStatusCode());

        assertTrue(responseDtoResponseEntity.getBody().getSuccess());
    }




}
