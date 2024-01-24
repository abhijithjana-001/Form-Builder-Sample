package com.example.Form.Builder.service;

import com.example.Form.Builder.dto.response.ResponseDto;
import com.example.Form.Builder.entities.entity.Form;
import com.example.Form.Builder.entities.entity.FormComponent;
import com.example.Form.Builder.repository.SqlRepo;
import com.example.Form.Builder.service.impl.SqlFormService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(properties = "current.database=mysql")
public class SqlFormServiceTest {
    @SpyBean
    private SqlFormService sqlFormService;

    @SpyBean
    private SqlRepo sqlRepo;


    private Form form;
    FormComponent formComponent;
    @BeforeEach
    void init(){
        formComponent = new FormComponent();
        formComponent.setType("test box");
        formComponent.setLabel("Enter your name");
        form=new Form(1L,"title",Arrays.asList(formComponent, formComponent));

    }
    @Test
    void saveTest(){
//               arrange
     doReturn(form).when(sqlRepo).save(any(Form.class));
//     act
        ResponseDto<Object> save = sqlFormService.saveOrUpdateForm(form, null);
//        assert
       assertTrue(save.getSuccess());
       assertEquals("Form created or updated successfully",save.getMessage());
       assertEquals(form,save.getResult());

    }

    @Test
    void UpdateTest(){
//               arrange
        doReturn(form).when(sqlRepo).save(any(Form.class));
        doReturn(Optional.of(form)).when(sqlRepo).findByTitle(anyString());

//     act
        ResponseDto<Object> save = sqlFormService.saveOrUpdateForm(form, "title");
//        assert
        assertTrue(save.getSuccess());
        assertEquals("Form created or updated successfully",save.getMessage());
        assertEquals(form,save.getResult());

    }

    @Test
    void deleteFormByTitleTest(){
//        assert
        doNothing().when(sqlRepo).deleteByTitle(anyString());
//        act
        ResponseDto<Void> deleteResponse = sqlFormService.deleteFormByTitle("title");
//        assert
        verify(sqlRepo,times(1)).deleteByTitle(anyString());
    }

    @Test
    void getFormByTitleTest(){
//        arrange
        doReturn(Optional.of(form)).when(sqlRepo).findByTitle(anyString());
//        act
        ResponseDto<Object> formResp = sqlFormService.getFormByTitle("title");
//        assert
        assertTrue(formResp.getSuccess());
        assertEquals("Successfully found",formResp.getMessage());
        assertEquals(form,formResp.getResult());

    }

    @Test
    void findByTitleStartingWithLetterTest(){
//        arrange
        doReturn(Arrays.asList(form,form)).when(sqlRepo).findByTitleStartingWithLetter(anyString());
//        act
        ResponseDto<List<Form>> forms = sqlFormService.findByTitleStartingWithLetter("t");
//        assert
        assertTrue(forms.getSuccess());
        assertEquals("List of forms",forms.getMessage());
        assertEquals(2,forms.getResult().size());
    }

    @Test
    void getFormsWithEmptyComponents(){
//        arrange
        doReturn(Arrays.asList(form,form)).when(sqlRepo).emptyFormComponent();
//        act
        ResponseDto<Object> forms = sqlFormService.getFormsWithEmptyComponents();
//        assert
        assertTrue(forms.getSuccess());
        assertEquals("List of forms",forms.getMessage());


    }


}
