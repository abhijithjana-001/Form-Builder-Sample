package com.example.Form.Builder.config.database;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "secondEntityManagerFactoryBean",
        basePackages = {"com.example.Form.Builder.repository"},
        transactionManagerRef ="secondTransactionManager"
)

@ConditionalOnProperty(name = "current.database", havingValue = "postgresql")
public class PostGreConfig {
    @Autowired
    private Environment environment;

    @Primary
    @Bean(name = "secondDatasource")
    public DataSource dataSource(){
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setUrl(environment.getProperty("second.datasource.url"));
        dataSource.setDriverClassName(environment.getProperty("second.datasource.driverClassName"));
        dataSource.setUsername(environment.getProperty("second.datasource.username"));
        dataSource.setPassword(environment.getProperty("second.datasource.password"));

        return  dataSource;
    }

    @Primary
    @Bean(name = "secondEntityManagerFactoryBean")

    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean bean=new LocalContainerEntityManagerFactoryBean();

        bean.setDataSource(dataSource());

        JpaVendorAdapter adapter=new HibernateJpaVendorAdapter();
        bean.setJpaVendorAdapter(adapter);

        Map<String,String> props=new HashMap<>();
        props.put("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
        props.put("hibernate.show_sql","true");
        props.put("hibernate.hbm2ddl.auto","update");

        bean.setJpaPropertyMap(props);

        bean.setPackagesToScan("com.example.Form.Builder.entities.entity");


        return bean;

    }

    @Primary
    @Bean(name = "secondTransactionManager")
    public PlatformTransactionManager transactionManager(){

        JpaTransactionManager manager=new JpaTransactionManager();

        manager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return  manager;
    }
}
