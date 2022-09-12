package br.com.fiap.livraria.configuration;

import br.com.fiap.livraria.service.LivrariaService;
import br.com.fiap.livraria.service.LivrariaServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public LivrariaService livrariaService(){
        return new LivrariaServiceImpl();
    }

}
