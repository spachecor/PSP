package com.spachecor.microservicioclienteasincrono;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executor;

@SpringBootApplication
public class MicroServicioClienteAsincronoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroServicioClienteAsincronoApplication.class, args);
    }
    @Bean
    public RestTemplate template() {
        return new RestTemplate();
    }
    @Bean
    public Executor executor(){return new ThreadPoolTaskExecutor();}
}
