package com.infy.app.main;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EntityScan
@SpringBootApplication
public class JunitValidationGitHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(JunitValidationGitHubApplication.class, args);
	}
	
	@Bean
	public ModelMapper mp()
	{
		return new ModelMapper();
	}

}
