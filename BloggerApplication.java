package com.blogger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.blogger.security.AppProperties;

@SpringBootApplication
public class BloggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloggerApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}
	
	@Bean(name ="AppProperties")
	public AppProperties getAppProperties()
	{
		return new AppProperties();
	}
}
