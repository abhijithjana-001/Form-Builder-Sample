package com.example.Form.Builder.service;

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
        if ("mongo".equals(dbName)) {
            return mongoFormService;
        } else {
            return sqlFormService;
        }
    }
}
