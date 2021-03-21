package com.xzymon.rabbitsubscriber.config;

import com.xzymon.rabbitsubscriber.credentials.CredentialsManager;
import com.xzymon.rabbitsubscriber.fun.DataSourceMock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

@PropertySource("${rabbitsubscriber.config.location}")
@Import(CredentialsConfig.class)
@Configuration
public class DatabaseConfig {
	private static final String USERNAME_PROPERTY_NAME = "database.conn.user";
	private static final String PASSWORD_PROPERTY_NAME = "database.conn.pass";

	@Bean
	DataSourceMock dataSourceMock(CredentialsManager credentialsManager, @Value("${database.conn.url}") String connUrl) {
		DataSourceMock mock = new DataSourceMock(connUrl, credentialsManager.get(USERNAME_PROPERTY_NAME), credentialsManager.get(PASSWORD_PROPERTY_NAME));

		System.out.println("DataSourceMock -- URL is: " + mock.getConnectionUrl());
		System.out.println("DataSourceMock -- Username is: " + mock.getUsername());
		System.out.println("DataSourceMock -- Password is: " + mock.getPassword());

		return mock;
	}

	@Bean(name = "oracleDataSource")
	@Primary
	public DataSource oracleDataSource(CredentialsManager credentialsManager /*, @Value("${database.conn.url}") String connUrl*/) {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.url("jdbc:oracle:thin:@//127.0.0.1:1521/pdb1");
		dataSourceBuilder.username(credentialsManager.get(USERNAME_PROPERTY_NAME));
		dataSourceBuilder.password(credentialsManager.get(PASSWORD_PROPERTY_NAME));
		return dataSourceBuilder.build();
	}
}
