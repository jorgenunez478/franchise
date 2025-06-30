package com.example.demo.api.application.port.in;

import com.example.demo.api.domain.model.Franchise;
import reactor.core.publisher.Mono;

public interface CreateFranchiseUseCase {
    Mono<Franchise> createFranchise(String name);
}