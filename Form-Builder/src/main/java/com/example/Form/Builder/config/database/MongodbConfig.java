//package com.example.Form.Builder.config.database;
//
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//
//import java.util.Objects;
//
//@Configuration
//@ConditionalOnProperty(name = "current.database", havingValue = "mongodb")
//@EnableMongoRepositories(basePackages = {"com.example.Form.Builder.repository"})
//public class MongodbConfig {
//    @Autowired
//    private Environment environment;
//
//    @Bean
//    public MongoClient mongoClient() {
//        return MongoClients.create(Objects.requireNonNull(environment.getProperty("spring.data.mongodb.uri")));
//    }
//
//    @Bean
//    public MongoTemplate mongoTemplate() {
//        return new MongoTemplate(mongoClient(), Objects.requireNonNull(environment.getProperty("spring.data.mongodb.database")));
//    }
//}
//
