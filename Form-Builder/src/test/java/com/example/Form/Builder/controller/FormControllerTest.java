package com.example.Form.Builder.controller;

import com.example.Form.Builder.dto.request.FormDto;
import com.example.Form.Builder.dto.response.ResponseDto;
import com.example.Form.Builder.entities.entity.Form;
import com.example.Form.Builder.entities.entity.FormComponent;
import com.example.Form.Builder.service.FormService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FormControllerTest {
    @SpyBean
    private FormService formService;

    @SpyBean
    private  FormController formController;

    private  FormDto formDto;

    private  Form form;

    @BeforeEach
    void init(){
        FormComponent formComponent = new FormComponent();
        formComponent.setType("test box");
        formComponent.setLabel("Enter your name");
        FormDto formDto=new FormDto(
                "Registration form",
                Arrays.asList(
                        formComponent, formComponent
                ));
        form=new Form(1L,"title",Arrays.asList(formComponent, formComponent));

    }


    @Test
    void testAddFormCreate() {
//     assert

        doReturn(new ResponseDto<Object>(true,"Form created successfully",form)).when(formService).saveOrUpdateForm(any(),isNull());

        // Act
        ResponseEntity<ResponseDto<Object>> form = formController.createForm(formDto);
        //        assert
        assertEquals(HttpStatusCode.valueOf(200),form.getStatusCode());
        assertEquals("Form created successfully", Objects.requireNonNull(form.getBody()).getMessage());
        assertTrue(form.getBody().getSuccess());
    }

    @Test
    void testAddFormUpdate() {
        doReturn(new ResponseDto<Object>(true,"Form updated successfully",form)).when(formService).saveOrUpdateForm(any(),anyString());
        // Act
        ResponseEntity<ResponseDto<Object>> form = formController.updateForm(formDto, "title");
        //        assert
        assertEquals(HttpStatusCode.valueOf(200),form.getStatusCode());
        assertEquals("Form updated successfully", Objects.requireNonNull(form.getBody()).getMessage());
        assertTrue(form.getBody().getSuccess());
    }

    @Test
    void testGetForm(){
//        assert
        doReturn(new ResponseDto<>(true,"Successfully found",form)).when(formService).getFormByTitle(anyString());
//        act
        ResponseEntity<ResponseDto<Object>> formByTitle = formController.getFormByTitle(anyString());
//        assert
        assertEquals(HttpStatusCode.valueOf(200),formByTitle.getStatusCode());
        assertEquals("Successfully found", Objects.requireNonNull(formByTitle.getBody()).getMessage());
        assertTrue(formByTitle.getBody().getSuccess());
    }


    @Test
    void deleteByTitle(){
//        arrange
doReturn(new ResponseDto<Form>(true,"deleted successfully",null)).when(formService).deleteFormByTitle(anyString());
//        act
        ResponseEntity<ResponseDto<Void>> deleteResponse = formController.deleteFormByTitle(anyString());

//        assert
        assertEquals(HttpStatusCode.valueOf(200),deleteResponse.getStatusCode());
        assertEquals("deleted successfully", Objects.requireNonNull(deleteResponse.getBody()).getMessage());
        assertTrue(deleteResponse.getBody().getSuccess());
    }


    @Test
    void getAllComponents()
    {
//        arrange
        doReturn( new ResponseDto<>())
                .when(formService).findByTitleStartingWithLetter(anyString());
//        act
        ResponseEntity<ResponseDto<List<Form>>> components = formController.findFormWithTile("a");
//        assert
        assertEquals(HttpStatusCode.valueOf(200),components.getStatusCode());

    }

    @Test
    void getEmptyComponents(){
//        arrange
        doReturn(new ResponseDto<>())
                .when(formService).getFormsWithEmptyComponents();
//        act
        ResponseEntity<ResponseDto<Object>> emptyComponent = formController.getEmptyComponent();
//        assert
        assertEquals(HttpStatusCode.valueOf(200),emptyComponent.getStatusCode());
    }
}
