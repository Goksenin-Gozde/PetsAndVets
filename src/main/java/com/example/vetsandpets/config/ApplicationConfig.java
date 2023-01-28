package com.example.vetsandpets.config;

import com.example.vetsandpets.service.DataInitializer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    CommandLineRunner init() {
        return new DataInitializer();
    }
}
