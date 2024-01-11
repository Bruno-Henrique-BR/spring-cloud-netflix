package com.curso.microservicopagamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroservicopagamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicopagamentoApplication.class, args);
	}

}
