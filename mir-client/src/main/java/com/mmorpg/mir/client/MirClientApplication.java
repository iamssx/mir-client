package com.mmorpg.mir.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MirClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(MirClientApplication.class, args);
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {

		}));
	}
}
