package com.example.musculia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MusculiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusculiaApplication.class, args);
	}

}
