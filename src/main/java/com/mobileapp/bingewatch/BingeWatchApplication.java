package com.mobileapp.bingewatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class BingeWatchApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BingeWatchApplication.class, args);
	}
}
