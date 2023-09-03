package com.example.tablebooker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class TablebookerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TablebookerApplication.class, args);
	}

}
