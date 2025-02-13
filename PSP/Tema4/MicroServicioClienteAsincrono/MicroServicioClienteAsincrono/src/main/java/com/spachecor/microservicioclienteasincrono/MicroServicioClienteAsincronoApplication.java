package com.spachecor.microservicioclienteasincrono;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executor;

@EnableAsync
@SpringBootApplication
public class MicroServicioClienteAsincronoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroServicioClienteAsincronoApplication.class, args);
    }
    @Bean
    public RestTemplate template() {
        return new RestTemplate();
    }
    // Configuración correcta del ThreadPoolTaskExecutor
    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);    // Número mínimo de hilos
        executor.setMaxPoolSize(8);     // Número máximo de hilos
        executor.setQueueCapacity(100); // Capacidad de cola para tareas pendientes
        executor.setThreadNamePrefix("Async-");
        executor.initialize();
        return executor;
    }
}
