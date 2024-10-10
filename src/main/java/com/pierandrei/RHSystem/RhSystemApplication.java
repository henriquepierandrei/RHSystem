package com.pierandrei.RHSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RhSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(RhSystemApplication.class, args);
	}

}
