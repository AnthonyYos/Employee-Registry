package com.anthony.employeeCrud.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
@EnableJpaRepositories(basePackages = "${spring.data.jpa.repository.packages}")
public class DataSourceConfig {

	// Primary datasource to look at, references employee database
	@Primary
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource appData() {
		return DataSourceBuilder.create().build();
	}

	// Define entityManager due to multiple datasources being used?
	@Bean
	@ConfigurationProperties(prefix = "spring.data.jpa.entity")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, DataSource appData) {
		return builder.dataSource(appData).build();
	}
	
	// Security data source to be used in SecurityConfig file to map to login database
	// Edit: login data table doesn't actually need to be in a different db
	/*
	 * @Bean
	 * 
	 * @ConfigurationProperties(prefix = "security.datasource") public DataSource
	 * securityData() { return DataSourceBuilder.create().build(); }
	 */
}