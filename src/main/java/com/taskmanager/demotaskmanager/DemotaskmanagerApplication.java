package com.taskmanager.demotaskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.taskmanager.demotaskmanager")
public class DemotaskmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemotaskmanagerApplication.class, args);
	}

}
