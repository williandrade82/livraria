package br.com.fiap.livraria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class LivrariaFiapApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivrariaFiapApplication.class, args);
	}

}
