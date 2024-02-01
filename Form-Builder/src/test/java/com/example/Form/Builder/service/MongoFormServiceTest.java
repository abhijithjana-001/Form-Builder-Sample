package com.example.Form.Builder.service;


import com.example.Form.Builder.dto.response.ResponseDto;
import com.example.Form.Builder.entities.entity.Form;
import com.example.Form.Builder.entities.mongoEntity.FormComponent;
import com.example.Form.Builder.entities.mongoEntity.MongodbForm;
import com.example.Form.Builder.repository.MongodbRepo;
import com.example.Form.Builder.service.impl.MongoFormService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = "current.database=mongodb")
@SpringBootTest
public class MongoFormServiceTest {
    @SpyBean
    private MongoFormService mongoFormService;

    @SpyBean
    private  MongodbRepo mongodbRepo;

   static MongoDBContainer noSqlContainer = new MongoDBContainer(DockerImageName.parse("mongo:latest"));

    private MongodbForm form;
    private Form updatedForm;

//    static {
//        GenericContainer<?> noSqlContainer =
//                new GenericContainer<>(DockerImageName.parse("redis:5.0.3-alpine")).withExposedPorts(6379);
//        redis.start();
//        System.setProperty("spring.redis.host", redis.getHost());
//        System.setProperty("spring.redis.port", redis.getMappedPort(6379).toString());
//    }

    @DynamicPropertySource
    public static void configureProperty(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", noSqlContainer::getReplicaSetUrl);
        registry.add("current.database",()->"redis");
    }
    @BeforeAll
    static void beforeAll() {

        noSqlContainer.start();
    }

    @AfterAll
    static void afterAll() {

        noSqlContainer.stop();
    }


    @BeforeEach
    void init(){

        form=new MongodbForm("1","titles", List.of());
        updatedForm=new Form(1L,"title", List.of());
        mongodbRepo.deleteAll();
    }
    @Test
    void verifyContainerIsRunning() {
        assertTrue(noSqlContainer.isRunning());
    }

    @Test
    void saveTest(){
//        arrange

//           doReturn(form).when(mongodbRepo).save(any(MongodbForm.class));
//           act
        ResponseDto<Object> saveResp = mongoFormService.saveOrUpdateForm(updatedForm, null);
//        assert
        assertTrue(saveResp.getSuccess());
        assertEquals("Form created successfully",saveResp.getMessage());
    }

    @Test
    void updateTest(){
//        arrange
    mongodbRepo.save(new MongodbForm("2","title2", List.of(new FormComponent())));
//        doReturn(Optional.of(form)).when(mongodbRepo).findByTitle(anyString());
//        doReturn(form).when(mongodbRepo).save(any(MongodbForm.class));
//        act
        ResponseDto<Object> updateResp = mongoFormService.saveOrUpdateForm(updatedForm, "title2");
        //        assert
        assertTrue(updateResp.getSuccess());
        assertEquals("Form updated successfully",updateResp.getMessage());
    }
    @Test
    void deleteFormByTitleTest(){
//        arrange
        mongodbRepo.save(form);
//        doNothing().when(mongodbRepo).deleteByTitle(anyString());
//        act
        ResponseDto<Void> deleteResp = mongoFormService.deleteFormByTitle("title");
//        assert
        verify(mongodbRepo,timeout(1)).deleteByTitle(anyString());
    }

    @Test
    void getFormByTitleTest(){
//        arrange
        mongodbRepo.save(form);
//        doReturn(Optional.of(form)).when(mongodbRepo).findByTitle(anyString());
//        act
        ResponseDto<Object> formResp = mongoFormService.getFormByTitle("titles");
//        assert
assertTrue(formResp.getSuccess());
assertEquals("Successfully found",formResp.getMessage());
assertEquals(form,formResp.getResult());
    }

    @Test
    void findFormWithStartingLetter(){
//        arrange
        mongodbRepo.save(form);
//        doReturn(Arrays.asList(form,form)).when(mongodbRepo).findByMongoFormStartingWithLetter(anyString());
//        act
        ResponseDto<List<Form>> forms = mongoFormService.findByTitleStartingWithLetter("t");
//        assert
        assertTrue(forms.getSuccess());
        assertEquals(1,forms.getResult().size());
        assertEquals("Successfully found",forms.getMessage());

    }

    @Test
    void getFormsWithEmptyComponentsTest(){
//        arrange

//        doReturn(Arrays.asList(form,form)).when(mongodbRepo).findFormsWithEmptyComponents();
//        act
        ResponseDto<Object> formsWithEmptyComponents = mongoFormService.getFormsWithEmptyComponents();


//       assert
        assertTrue(formsWithEmptyComponents.getSuccess());
        assertEquals("Successfully found",formsWithEmptyComponents.getMessage());

    }
}
