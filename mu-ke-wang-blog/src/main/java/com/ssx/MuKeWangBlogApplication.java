package com.ssx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class MuKeWangBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(MuKeWangBlogApplication.class, args);
	}

	@GetMapping("/")
	public String hello() {
		return "helljkjkjko2222";
	}
}
