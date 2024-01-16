package com.example.Form.Builder;

import com.example.Form.Builder.entities.entity.Form;
import com.example.Form.Builder.entities.entity.FormComponent;
import com.example.Form.Builder.repostory.SqlRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class FormBuilderApplication {
//@Autowired
//	private SqlRepo sqlRepo;

	public static void main(String[] args) {
		SpringApplication.run(FormBuilderApplication.class, args);
	}


//	@PostConstruct
//	public void run() {
//
//		Form form=new Form();
//		form.setTitle("registration52222");
//		FormComponent formComponent=new FormComponent();
//		formComponent.setType("text");
//		formComponent.setLabel("enter");
//		FormComponent formComponent1=new FormComponent();
//		formComponent1.setType("text1");
//		formComponent1.setLabel("enter1");
//		FormComponent formComponent2=new FormComponent();
//		formComponent2.setType("text2");
//		formComponent2.setLabel("enter2");
//      form.setComponents(Arrays.asList(formComponent,formComponent1,formComponent2));
//
//		System.out.println(sqlRepo.save(form));
//
//
//	}
}
