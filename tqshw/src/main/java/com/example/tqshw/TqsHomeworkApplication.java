package com.example.tqshw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TqsHomeworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(TqsHomeworkApplication.class, args);
	}

}
