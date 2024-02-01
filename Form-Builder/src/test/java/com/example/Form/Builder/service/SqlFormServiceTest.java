package com.example.Form.Builder.service;

import com.example.Form.Builder.dto.response.ResponseDto;
import com.example.Form.Builder.entities.entity.Form;
import com.example.Form.Builder.entities.entity.FormComponent;
import com.example.Form.Builder.repository.SqlRepo;
import com.example.Form.Builder.service.impl.SqlFormService;
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
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SqlFormServiceTest {
    @SpyBean
    private SqlFormService sqlFormService;

    @SpyBean
    private SqlRepo sqlRepo;


    @Container
     static MySQLContainer<?> sqlContainer = new MySQLContainer<>("mysql:latest");

//    @Container
//     static PostgreSQLContainer<?> sqlContainer = new PostgreSQLContainer<>("postgres:latest");

//    @Container
//    static  OracleContainer sqlContainer = new OracleContainer(DockerImageName.parse("gvenzl/oracle-xe:21-slim-faststart"));


    @DynamicPropertySource
    public static void configureProperty(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", sqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", sqlContainer::getUsername);
        registry.add("spring.datasource.password", sqlContainer::getPassword);
        registry.add("current.database",()->"mysql");
    }


    @BeforeAll
    static void beforeAll() {

        sqlContainer.start();
    }

    @AfterAll
    static void afterAll() {

        sqlContainer.stop();
    }


    private Form form;
    FormComponent formComponent;

    @BeforeEach
    void init() {
        formComponent = new FormComponent();
        formComponent.setType("test box");
        formComponent.setLabel("Enter your name");
        form = new Form(1L, "title", List.of(formComponent));

    }

    @Test
    void verifyContainerIsRunning() {
        assertTrue(sqlContainer.isRunning());
    }

    @Test
    void saveTest() {
////               arrange
//     doReturn(form).when(sqlRepo).save(any(Form.class));
//     act
        ResponseDto<Object> save = sqlFormService.saveOrUpdateForm(form, null);
//        assert
        assertTrue(save.getSuccess());
        assertEquals("Form created or updated successfully", save.getMessage());
        assertEquals(form, save.getResult());

    }

    @Test
    void UpdateTest() {
//               arrange
//        doReturn(form).when(sqlRepo).save(any(Form.class));
//        doReturn(Optional.of(form)).when(sqlRepo).findByTitle(anyString());


//     act
        ResponseDto<Object> save = sqlFormService.saveOrUpdateForm(form, "title");
//        assert
        assertTrue(save.getSuccess());
        assertEquals("Form created or updated successfully", save.getMessage());
        assertEquals(form, save.getResult());

    }

    @Test
    void deleteFormByTitleTest() {
//        assert
//        doNothing().when(sqlRepo).deleteByTitle(anyString());

//        act
        ResponseDto<Void> deleteResponse = sqlFormService.deleteFormByTitle("title");
//        assert
        verify(sqlRepo, times(1)).deleteByTitle(anyString());
    }

    @Test
    void getFormByTitleTest() {
//        arrange
//        doReturn(Optional.of(form)).when(sqlRepo).findByTitle(anyString());
        sqlRepo.save(form);
//        act
        ResponseDto<Object> formResp = sqlFormService.getFormByTitle("title");
//        assert
        assertTrue(formResp.getSuccess());
        assertEquals("Successfully found", formResp.getMessage());
        assertEquals(form, formResp.getResult());

    }

    @Test
    void findByTitleStartingWithLetterTest() {
//        arrange
//        doReturn(Arrays.asList(form, form)).when(sqlRepo).findByTitleStartingWithLetter(anyString());

        sqlRepo.save(form);
//        act
        ResponseDto<List<Form>> forms = sqlFormService.findByTitleStartingWithLetter("t");
//        assert
        assertTrue(forms.getSuccess());
        assertEquals("List of forms", forms.getMessage());
        assertEquals(1, forms.getResult().size());
    }

    @Test
    void getFormsWithEmptyComponents() {
//        arrange
//        doReturn(Arrays.asList(form, form)).when(sqlRepo).emptyFormComponent();
//        act
        ResponseDto<Object> forms = sqlFormService.getFormsWithEmptyComponents();
//        assert
        assertTrue(forms.getSuccess());
        assertEquals("List of forms", forms.getMessage());

    }


}
