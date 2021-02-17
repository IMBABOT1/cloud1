package ru.geekbrains.cloud.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class SpringConfig {

    @Bean
    public Server server(){
        return new Server();
    }

    @Bean
    public SqlAuthManager sqlAuthManager(DataSource DataSource) throws SQLException, ClassNotFoundException {
        return new SqlAuthManager(dataSource());
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUsername("");
        dataSource.setPassword("");
        dataSource.setUrl("org.springframework.jdbc.datasource.DriverManagerDataSource");

        return dataSource;
    }
}
