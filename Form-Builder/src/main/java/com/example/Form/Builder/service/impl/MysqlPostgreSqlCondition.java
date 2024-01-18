package com.example.Form.Builder.service.impl;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;



public class MysqlPostgreSqlCondition implements Condition
{


    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata)
    {
        String database = context.getEnvironment().getProperty("current.database");
        return database.equalsIgnoreCase("mysql") || database.equalsIgnoreCase("postgresql");
    }
}