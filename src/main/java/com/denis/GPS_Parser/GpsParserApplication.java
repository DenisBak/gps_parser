package com.denis.GPS_Parser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class GpsParserApplication {
	public static void main(String[] args) {
		SpringApplication.run(GpsParserApplication.class, args);
	}
}
