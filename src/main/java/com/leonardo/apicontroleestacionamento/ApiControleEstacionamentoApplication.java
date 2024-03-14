package com.leonardo.apicontroleestacionamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ApiControleEstacionamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiControleEstacionamentoApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("senha123"));
	}

}
