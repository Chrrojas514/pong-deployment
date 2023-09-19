package com.chris.pongsql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PongSqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(PongSqlApplication.class, args);
	}

}
