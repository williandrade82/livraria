package br.com.fiap.livraria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class LivrariaFiapApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivrariaFiapApplication.class, args);
	}

}
