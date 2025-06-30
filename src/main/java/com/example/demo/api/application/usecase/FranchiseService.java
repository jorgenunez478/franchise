package com.example.demo.api.application.usecase;

import com.example.demo.api.application.port.in.*;
import com.example.demo.api.application.port.out.FranchiseRepositoryPort;
import com.example.demo.api.domain.exception.FranchiseNotFoundException;
import com.example.demo.api.domain.model.Branch;
import com.example.demo.api.domain.model.Franchise;
import com.example.demo.api.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FranchiseService implements
        CreateFranchiseUseCase,
        AddBranchToFranchiseUseCase,
        AddProductToBranchUseCase,
        DeleteProductFromBranchUseCase,
        UpdateProductStockUseCase,
        GetProductWithMostStockUseCase,
        UpdateFranchiseNameUseCase,
        UpdateBranchNameUseCase,
        UpdateProductNameUseCase {

    private final FranchiseRepositoryPort franchiseRepositoryPort;

    @Override
    public Mono<Franchise> createFranchise(String name) {
        Franchise newFranchise = new Franchise(name, List.of());
        return franchiseRepositoryPort.save(newFranchise);
    }

    @Override
    public Mono<Franchise> addBranchToFranchise(String franchiseId, Branch newBranch) {
        return franchiseRepositoryPort.findById(franchiseId)
                .switchIfEmpty(Mono.error(new FranchiseNotFoundException(franchiseId)))
                .map(franchise -> {
                    franchise.addBranch(newBranch);
                    return franchise;
                })
                .flatMap(franchiseRepositoryPort::save);
    }

    @Override
    public Mono<Franchise> addProductToBranch(String franchiseId, String branchId, Product newProduct) {
        return franchiseRepositoryPort.findById(franchiseId)
                .switchIfEmpty(Mono.error(new FranchiseNotFoundException(franchiseId)))
                .map(franchise -> {
                    Branch targetBranch = franchise.getBranches().stream()
                            .filter(b -> b.getId().equals(branchId))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Sucursal no encontrada con ID: " + branchId)); // Usar excepción de dominio
                    targetBranch.addProduct(newProduct);
                    return franchise;
                })
                .flatMap(franchiseRepositoryPort::save);
    }

    @Override
    public Mono<Franchise> deleteProductFromBranch(String franchiseId, String branchId, String productId) {
        return franchiseRepositoryPort.findById(franchiseId)
                .switchIfEmpty(Mono.error(new FranchiseNotFoundException(franchiseId)))
                .map(franchise -> {
                    Branch targetBranch = franchise.getBranches().stream()
                            .filter(b -> b.getId().equals(branchId))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Sucursal no encontrada con ID: " + branchId));
                    targetBranch.removeProduct(productId);
                    return franchise;
                })
                .flatMap(franchiseRepositoryPort::save);
    }

    @Override
    public Mono<Franchise> updateProductStock(String franchiseId, String branchId, String productId, int newStock) {
        return franchiseRepositoryPort.findById(franchiseId)
                .switchIfEmpty(Mono.error(new FranchiseNotFoundException(franchiseId)))
                .map(franchise -> {
                    Branch targetBranch = franchise.getBranches().stream()
                            .filter(b -> b.getId().equals(branchId))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Sucursal no encontrada con ID: " + branchId));
                    Product targetProduct = targetBranch.getProductById(productId);
                    if (targetProduct == null) {
                        throw new RuntimeException("Producto no encontrado con ID: " + productId);
                    }
                    targetProduct.updateStock(newStock);
                    return franchise;
                })
                .flatMap(franchiseRepositoryPort::save);
    }

    @Override
    public Flux<Map.Entry<String, Product>> getProductWithMostStockByBranch(String franchiseId) {
        return franchiseRepositoryPort.findById(franchiseId)
                .switchIfEmpty(Mono.error(new FranchiseNotFoundException(franchiseId)))
                .flatMapMany(franchise ->
                        Flux.fromIterable(franchise.getBranches())
                                .map(branch -> {
                                    Product product = branch.getProductWithMostStock();
                                    return product != null ? Map.entry(branch.getName(), product) : null;
                                })
                                .filter(java.util.Objects::nonNull)
                );
    }

    @Override
    public Mono<Franchise> updateFranchiseName(String franchiseId, String newName) {
        return franchiseRepositoryPort.findById(franchiseId)
                .switchIfEmpty(Mono.error(new FranchiseNotFoundException(franchiseId)))
                .map(franchise -> {
                    franchise.setName(newName);
                    return franchise;
                })
                .flatMap(franchiseRepositoryPort::update); // Usar update para reflejar que es una actualización
    }

    @Override
    public Mono<Franchise> updateBranchName(String franchiseId, String branchId, String newName) {
        return franchiseRepositoryPort.findById(franchiseId)
                .switchIfEmpty(Mono.error(new FranchiseNotFoundException(franchiseId)))
                .map(franchise -> {
                    Branch targetBranch = franchise.getBranches().stream()
                            .filter(b -> b.getId().equals(branchId))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Sucursal no encontrada con ID: " + branchId));
                    targetBranch.setName(newName);
                    return franchise;
                })
                .flatMap(franchiseRepositoryPort::update);
    }

    @Override
    public Mono<Franchise> updateProductName(String franchiseId, String branchId, String productId, String newName) {
        return franchiseRepositoryPort.findById(franchiseId)
                .switchIfEmpty(Mono.error(new FranchiseNotFoundException(franchiseId)))
                .map(franchise -> {
                    Branch targetBranch = franchise.getBranches().stream()
                            .filter(b -> b.getId().equals(branchId))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Sucursal no encontrada con ID: " + branchId));
                    Product targetProduct = targetBranch.getProductById(productId);
                    if (targetProduct == null) {
                        throw new RuntimeException("Producto no encontrado con ID: " + productId);
                    }
                    targetProduct.setName(newName);
                    return franchise;
                })
                .flatMap(franchiseRepositoryPort::update);
    }
}