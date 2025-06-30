package com.example.demo.api.infrastructure.adapter.persistence.mongodb.repository;

import com.example.demo.api.infrastructure.adapter.persistence.mongodb.dto.FranchiseDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SpringDataFranchiseRepository extends ReactiveMongoRepository<FranchiseDocument, String> {
    // Puedes añadir métodos de búsqueda personalizados aquí si los necesitas
    Mono<FranchiseDocument> findByName(String name);
}