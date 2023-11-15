package com.example.demo.dbconfig;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class JdbcConfig {
	
	private static JdbcConfig instance;
	
	private JdbcConfig() {}

    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/Person");
        dataSource.setUsername("postgres");
        dataSource.setPassword("admin");
        return dataSource;
    }
    
    public static JdbcConfig getInstance() {
    	if(instance == null) {
    		instance = new JdbcConfig();
    	}
    	return instance;
    }
    
}

