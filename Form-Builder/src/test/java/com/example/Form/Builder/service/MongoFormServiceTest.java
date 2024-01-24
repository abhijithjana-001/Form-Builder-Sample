package com.example.Form.Builder.service;


import com.example.Form.Builder.dto.response.ResponseDto;
import com.example.Form.Builder.entities.entity.Form;
import com.example.Form.Builder.entities.mongoEntity.FormComponent;
import com.example.Form.Builder.entities.mongoEntity.MongodbForm;
import com.example.Form.Builder.repository.MongodbRepo;
import com.example.Form.Builder.service.impl.MongoFormService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(properties = "current.database=mongodb")
public class MongoFormServiceTest {
    @SpyBean
    private MongoFormService mongoFormService;

    @SpyBean
    private  MongodbRepo mongodbRepo;

    private MongodbForm form;

    @BeforeEach
    void init(){

        form=new MongodbForm("1","title", List.of());
    }

    @Test
    void saveTest(){
//        arrange
           doReturn(form).when(mongodbRepo).save(any(MongodbForm.class));
//           act
        ResponseDto<Object> saveResp = mongoFormService.saveOrUpdateForm(new Form(), null);
//        assert
        assertTrue(saveResp.getSuccess());
        assertEquals("Form created successfully",saveResp.getMessage());
    }

    @Test
    void updateTest(){
//        arrange
        doReturn(Optional.of(form)).when(mongodbRepo).findByTitle(anyString());
        doReturn(form).when(mongodbRepo).save(any(MongodbForm.class));
//        act
        ResponseDto<Object> updateResp = mongoFormService.saveOrUpdateForm(new Form(), "title");
        //        assert
        assertTrue(updateResp.getSuccess());
        assertEquals("Form updated successfully",updateResp.getMessage());
    }
    @Test
    void deleteFormByTitleTest(){
//        arrange
        doNothing().when(mongodbRepo).deleteByTitle(anyString());
//        act
        ResponseDto<Void> deleteResp = mongoFormService.deleteFormByTitle("title");
//        assert
        verify(mongodbRepo,timeout(1)).deleteByTitle(anyString());
    }

    @Test
    void getFormByTitleTest(){
//        arrange
        doReturn(Optional.of(form)).when(mongodbRepo).findByTitle(anyString());
//        act
        ResponseDto<Object> formResp = mongoFormService.getFormByTitle(anyString());
//        assert
assertTrue(formResp.getSuccess());
assertEquals("Successfully found",formResp.getMessage());
    }

    @Test
    void findFormWithStartingLetter(){
//        arrange
        doReturn(Arrays.asList(form,form)).when(mongodbRepo).findByMongoFormStartingWithLetter(anyString());
//        act
        ResponseDto<List<Form>> forms = mongoFormService.findByTitleStartingWithLetter("t");
//        assert
        assertTrue(forms.getSuccess());
        assertEquals(2,forms.getResult().size());
        assertEquals("Successfully found",forms.getMessage());

    }

    @Test
    void getFormsWithEmptyComponentsTest(){
//        arrange
        doReturn(Arrays.asList(form,form)).when(mongodbRepo).findFormsWithEmptyComponents();
//        act
        ResponseDto<Object> formsWithEmptyComponents = mongoFormService.getFormsWithEmptyComponents();


//       assert
        assertTrue(formsWithEmptyComponents.getSuccess());
        assertEquals("Successfully found",formsWithEmptyComponents.getMessage());

    }
}
