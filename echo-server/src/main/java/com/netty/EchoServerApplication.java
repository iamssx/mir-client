package com.netty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EchoServerApplication implements CommandLineRunner{

	@Autowired
	private EchoServer echoServer;

	public static void main(String[] args) {
		SpringApplication.run(EchoServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		echoServer.start();
	}
}
