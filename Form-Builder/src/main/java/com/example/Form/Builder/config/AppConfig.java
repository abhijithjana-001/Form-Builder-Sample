package com.example.Form.Builder.config;

import com.example.Form.Builder.repository.SqlRepo;
import com.example.Form.Builder.service.FormService;
import com.example.Form.Builder.service.impl.MongoFormService;
import com.example.Form.Builder.service.impl.SqlFormService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.JpaRepository;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}