package com.example.demo.api.infrastructure.adapter.persistence.mongodb.adapter;

import com.example.demo.api.application.port.out.FranchiseRepositoryPort;
import com.example.demo.api.domain.model.Franchise;
import com.example.demo.api.infrastructure.adapter.persistence.mongodb.dto.FranchiseDocument;
import com.example.demo.api.infrastructure.adapter.persistence.mongodb.repository.SpringDataFranchiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FranchiseMongoAdapter implements FranchiseRepositoryPort {

    private final SpringDataFranchiseRepository springDataFranchiseRepository;

    @Override
    public Mono<Franchise> save(Franchise franchise) {
        return springDataFranchiseRepository.save(FranchiseDocument.fromDomain(franchise))
                .map(FranchiseDocument::toDomain);
    }

    @Override
    public Mono<Franchise> findById(String id) {
        return springDataFranchiseRepository.findById(id)
                .map(FranchiseDocument::toDomain);
    }

    @Override
    public Flux<Franchise> findAll() {
        return springDataFranchiseRepository.findAll()
                .map(FranchiseDocument::toDomain);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return springDataFranchiseRepository.deleteById(id);
    }

    @Override
    public Mono<Franchise> update(Franchise franchise) {
        // En MongoDB, `save` actúa como `update` si el ID ya existe
        return springDataFranchiseRepository.save(FranchiseDocument.fromDomain(franchise))
                .map(FranchiseDocument::toDomain);
    }
}