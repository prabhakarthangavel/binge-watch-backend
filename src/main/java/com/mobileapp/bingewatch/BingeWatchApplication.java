package com.mobileapp.bingewatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mobileapp.bingewatch.entity.Movies;
import com.mobileapp.bingewatch.entity.Posts;
import com.mobileapp.bingewatch.repo.MoviesRepository;
import com.mobileapp.bingewatch.repo.PostsRepository;

@SpringBootApplication
public class BingeWatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(BingeWatchApplication.class, args);
	}
}
