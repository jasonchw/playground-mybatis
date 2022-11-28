package com.playground.mybatis.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.io.InputStream;

@Configuration
public class DatabaseConfig {
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        final InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        final Resource configLocation = new InputStreamResource(inputStream);

        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setConfigLocation(configLocation);
        return sessionFactory.getObject();
    }
}
