//package com.example.Form.Builder.service;
//
//import com.example.Form.Builder.dto.response.ResponseDto;
//import com.example.Form.Builder.entities.entity.Form;
//import com.example.Form.Builder.entities.mongoEntity.MongoForm;
//import com.example.Form.Builder.repository.MongoRepo;
//import com.example.Form.Builder.repository.mysql.repo.FormRepo;
//import com.example.Form.Builder.repository.postgres.repo.PostgreRepo;
//import com.example.Form.Builder.service.impl.FormServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.SpyBean;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.doReturn;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//public class FormServiceTest {
//    @SpyBean
//    private FormService formService;
//
//    @SpyBean
//    private FormRepo formRepo;
//
//    @SpyBean
//    private PostgreRepo postgreRepo;
//
//    @SpyBean
//    private MongoRepo mongoRepo;
//
//
//    @Test
//    void FormCreate(){
////        arrange
//        doReturn(new Form()).when(formRepo).save(any(Form.class));
//        doReturn(new Form()).when(postgreRepo).save(any(Form.class));
//        doReturn(new MongoForm()).when(mongoRepo).save(any(MongoForm.class));
//
//
////        act
//        ResponseDto<List<Object>> listResponseDto = formService.saveOrUpdateForm(new Form(), null);
//
////        assert
//
//        assertEquals("Form created successfully",listResponseDto.getMessage());
//        assertTrue(listResponseDto.getSuccess());
//    }
//
//    @Test
//    void FormUpdate(){
////        arrange
//        doReturn(new Form()).when(formRepo).save(any(Form.class));
//        doReturn(new Form()).when(postgreRepo).save(any(Form.class));
//        doReturn(new MongoForm()).when(mongoRepo).save(any(MongoForm.class));
//
//        doReturn(Optional.of(new Form())).when(formRepo).findByTitle(anyString());
//
//        doReturn(Optional.of(new Form())).when(postgreRepo).findByTitle(anyString());
//
//        doReturn(Optional.of(new MongoForm())).when(mongoRepo).findByTitle(anyString());
//
//
//
////        act
//        ResponseDto<List<Object>> listResponseDto = formService.saveOrUpdateForm(new Form(),"title");
//
////        assert
//
//        assertEquals("Form updated successfully",listResponseDto.getMessage());
//        assertTrue(listResponseDto.getSuccess());
//    }
//
//    @Test
//    void deleteFormTitleTest(){
//        Form form=new Form();
//        form.setId(1L);
////        arrange
//        doReturn(Optional.of(form)).when(formRepo).findByTitle(anyString());
//       doNothing().when(formRepo).deleteById(anyLong());
//
//        doReturn(Optional.of(form)).when(postgreRepo).findByTitle(anyString());
//        doNothing().when(postgreRepo).deleteById(anyLong());
//
//        doNothing().when(mongoRepo).deleteByTitle(anyString());
//
////        act
//        formService.deleteFormByTitle("title");
////        assert
//        verify(formRepo,times(1)).deleteById(anyLong());
//        verify(postgreRepo,times(1)).deleteById(anyLong());
//        verify(mongoRepo,times(1)).deleteByTitle(anyString());
//    }
//
//    @Test
//    void testGetFormByTitle(){
////        arrange
//        doReturn(Optional.of(new Form())).when(formRepo).findByTitle(anyString());
////        act
//        ResponseDto<Form> title = formService.getFormByTitle("title");
////        assert
//        assertEquals("Successfully found with title",title.getMessage());
//        assertTrue(title.getSuccess());
//    }
//
//}
