package com.netty;

import com.netty.client.EchoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EchoClientApplication implements CommandLineRunner{

	@Autowired
	private EchoClient echoClient;

	public static void main(String[] args) {
		SpringApplication.run(EchoClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		echoClient.contect();
	}
}
