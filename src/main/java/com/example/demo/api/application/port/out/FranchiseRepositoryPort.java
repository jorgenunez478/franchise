package com.example.demo.api.application.port.out;

import com.example.demo.api.domain.model.Franchise;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

public interface FranchiseRepositoryPort {
    Mono<Franchise> save(Franchise franchise);
    Mono<Franchise> findById(String id);
    Flux<Franchise> findAll();
    Mono<Void> deleteById(String id);
    Mono<Franchise> update(Franchise franchise);
}