package com.example.demo.api.infrastructure.config;

import com.example.demo.api.application.usecase.FranchiseService;
import com.example.demo.api.infrastructure.adapter.persistence.mongodb.adapter.FranchiseMongoAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public FranchiseService franchiseService(FranchiseMongoAdapter franchiseMongoAdapter) {
        // Spring se encarga de inyectar FranchiseMongoAdapter
        return new FranchiseService(franchiseMongoAdapter);
    }

    // Podrías tener otros beans de configuración aquí
}