package com.buutcamp.config;

import com.buutcamp.databaselogic.ClientDao;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.buutcamp")
public class AppConfig {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver =
                new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    @Bean
    public DataSource secDataSource() {
        BasicDataSource secDataSource = new BasicDataSource();

        secDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        secDataSource.setUrl("jdbc:mysql://localhost:3306/final_project?serverTimezone=UTC&allowPublicKeyRetrieval=true");
        secDataSource.setUsername("finuser");
        secDataSource.setPassword("finpw");

        secDataSource.setInitialSize(1);
        secDataSource.setMaxConnLifetimeMillis(1000000);
        secDataSource.setMaxTotal(6);
        return secDataSource;
    }



    @Bean
    public ClientDao getClientInfoDao() {
        ClientDao dao = new ClientDao();
        DataSource ds = new BasicDataSource();
        ((BasicDataSource) ds).setUsername("finuser");
        ((BasicDataSource) ds).setPassword("finpw");
        ((BasicDataSource) ds).setDriverClassName("com.mysql.cj.jdbc.Driver");
        ((BasicDataSource) ds).setUrl("jdbc:mysql://localhost:3306/final_project?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");

        dao.setDataSource(ds);

        return dao;
    }
}
