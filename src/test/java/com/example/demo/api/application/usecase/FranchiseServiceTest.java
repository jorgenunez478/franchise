package com.example.demo.api.application.usecase;

import com.example.demo.api.application.usecase.FranchiseService;
import com.example.demo.api.application.port.out.FranchiseRepositoryPort;
import com.example.demo.api.domain.model.Branch;
import com.example.demo.api.domain.model.Franchise;
import com.example.demo.api.domain.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FranchiseServiceTest {

    @Mock
    FranchiseRepositoryPort franchiseRepositoryPort;

    @InjectMocks
    FranchiseService franchiseService;

    @Test
    void createFranchise_shouldReturnSavedFranchise() {
        Franchise franchise = new Franchise("Test", List.of());
        when(franchiseRepositoryPort.save(any())).thenReturn(Mono.just(franchise));

        Mono<Franchise> result = franchiseService.createFranchise("Test");

        StepVerifier.create(result)
                .expectNextMatches(f -> f.getName().equals("Test"))
                .verifyComplete();
    }

    @Test
    void addBranchToFranchise_shouldAddBranchAndReturnUpdatedFranchise() {
        Franchise franchise = new Franchise("Test", List.of());
        Branch newBranch = new Branch("Sucursal 1", List.of());
        Franchise updatedFranchise = new Franchise(franchise.getId(), franchise.getName(), List.of(newBranch));
    }

    @Test
    void addProductToBranch_shouldAddProductAndReturnUpdatedFranchise() {
        // Datos iniciales
        Branch branch = new Branch("Sucursal 1", List.of());
        Franchise franchise = new Franchise("Test", List.of(branch));
        Product newProduct = new Product("Producto 1", 10);

        // Sucursal actualizada con el producto agregado
        Branch updatedBranch = new Branch(branch.getId(), branch.getName(), List.of(newProduct));
        Franchise updatedFranchise = new Franchise(franchise.getId(), franchise.getName(), List.of(updatedBranch));

        when(franchiseRepositoryPort.findById(franchise.getId())).thenReturn(Mono.just(franchise));
        when(franchiseRepositoryPort.save(any())).thenReturn(Mono.just(updatedFranchise));

        Mono<Franchise> result = franchiseService.addProductToBranch(franchise.getId(), branch.getId(), newProduct);

        StepVerifier.create(result)
                .expectNextMatches(f -> f.getBranches().get(0).getProducts().size() == 1 &&
                        f.getBranches().get(0).getProducts().get(0).getName().equals("Producto 1"))
                .verifyComplete();
    }
}
