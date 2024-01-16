package com.example.Form.Builder.config;

import com.example.Form.Builder.service.FormService;
import com.example.Form.Builder.service.impl.MongoFormService;
import com.example.Form.Builder.service.impl.SqlFormService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ServiceConfig {
    @Value("${current.database}")
    private String dbName;

    @Bean
    @Primary
    public FormService primaryFormService(SqlFormService sqlFormService, MongoFormService mongoFormService) {
        if ("mongodb".equals(dbName)) {
            return mongoFormService;
        } else {
            return sqlFormService;
        }
    }
}