package com.cos.web01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class Web01Application {

	public static void main(String[] args) {
		SpringApplication.run(Web01Application.class, args);
	}

}
