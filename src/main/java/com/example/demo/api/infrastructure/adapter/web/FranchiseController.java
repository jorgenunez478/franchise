package com.example.demo.api.infrastructure.adapter.web;

import com.example.demo.api.application.port.in.*;
import com.example.demo.api.domain.model.Franchise;
import com.example.demo.api.domain.model.Product;
import com.example.demo.api.infrastructure.adapter.web.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/franchises")
@RequiredArgsConstructor
public class FranchiseController {

    private final CreateFranchiseUseCase createFranchiseUseCase;
    private final AddBranchToFranchiseUseCase addBranchToFranchiseUseCase;
    private final AddProductToBranchUseCase addProductToBranchUseCase;
    private final DeleteProductFromBranchUseCase deleteProductFromBranchUseCase;
    private final UpdateProductStockUseCase updateProductStockUseCase;
    private final GetProductWithMostStockUseCase getProductWithMostStockUseCase;
    private final UpdateFranchiseNameUseCase updateFranchiseNameUseCase;
    private final UpdateBranchNameUseCase updateBranchNameUseCase;
    private final UpdateProductNameUseCase updateProductNameUseCase;

    // Endpoint 2: Agregar nueva franquicia
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<FranchiseResponse> createFranchise(@Valid @RequestBody FranchiseRequest request) {
        return createFranchiseUseCase.createFranchise(request.getName())
                .map(FranchiseResponse::fromDomain);
    }

    // Endpoint 3: Agregar nueva sucursal a la franquicia
    @PostMapping("/{franchiseId}/branches")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<FranchiseResponse> addBranchToFranchise(@PathVariable String franchiseId,
                                                        @Valid @RequestBody BranchRequest request) {
        return addBranchToFranchiseUseCase.addBranchToFranchise(franchiseId, request.toDomain())
                .map(FranchiseResponse::fromDomain);
    }

    // Endpoint 4: Agregar nuevo producto a la sucursal
    @PostMapping("/{franchiseId}/branches/{branchId}/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<FranchiseResponse> addProductToBranch(@PathVariable String franchiseId,
                                                      @PathVariable String branchId,
                                                      @Valid @RequestBody ProductRequest request) {
        return addProductToBranchUseCase.addProductToBranch(franchiseId, branchId, request.toDomain())
                .map(FranchiseResponse::fromDomain);
    }

    // Endpoint 5: Eliminar un producto de una sucursal
    @DeleteMapping("/{franchiseId}/branches/{branchId}/products/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<FranchiseResponse> deleteProductFromBranch(@PathVariable String franchiseId,
                                                           @PathVariable String branchId,
                                                           @PathVariable String productId) {
        return deleteProductFromBranchUseCase.deleteProductFromBranch(franchiseId, branchId, productId)
                .map(FranchiseResponse::fromDomain);
    }

    // Endpoint 6: Modificar el Stock de un producto
    @PatchMapping("/{franchiseId}/branches/{branchId}/products/{productId}/stock")
    public Mono<FranchiseResponse> updateProductStock(@PathVariable String franchiseId,
                                                      @PathVariable String branchId,
                                                      @PathVariable String productId,
                                                      @Valid @RequestBody ProductStockUpdateRequest request) {
        return updateProductStockUseCase.updateProductStock(franchiseId, branchId, productId, request.getNewStock())
                .map(FranchiseResponse::fromDomain);
    }

    // Endpoint 7: Producto con más stock por sucursal para una franquicia puntual
    @GetMapping("/{franchiseId}/products/most-stock")
    public Flux<ProductWithBranchResponse> getProductWithMostStockByBranch(@PathVariable String franchiseId) {
        return getProductWithMostStockUseCase.getProductWithMostStockByBranch(franchiseId)
                .map(entry -> new ProductWithBranchResponse(entry.getKey(), entry.getValue()));
    }

    // PLUS: Actualizar nombre de franquicia
    @PatchMapping("/{franchiseId}/name")
    public Mono<FranchiseResponse> updateFranchiseName(@PathVariable String franchiseId, @Valid @RequestBody FranchiseNameUpdateRequest request) {
        return updateFranchiseNameUseCase.updateFranchiseName(franchiseId, request.getNewName())
                .map(FranchiseResponse::fromDomain);
    }

    // PLUS: Actualizar nombre de sucursal
    @PatchMapping("/{franchiseId}/branches/{branchId}/name")
    public Mono<FranchiseResponse> updateBranchName(@PathVariable String franchiseId,
                                                    @PathVariable String branchId,
                                                    @Valid @RequestBody BranchNameUpdateRequest request) {
        return updateBranchNameUseCase.updateBranchName(franchiseId, branchId, request.getNewName())
                .map(FranchiseResponse::fromDomain);
    }

    // PLUS: Actualizar nombre de producto
    @PatchMapping("/{franchiseId}/branches/{branchId}/products/{productId}/name")
    public Mono<FranchiseResponse> updateProductName(@PathVariable String franchiseId,
                                                     @PathVariable String branchId,
                                                     @PathVariable String productId,
                                                     @Valid @RequestBody ProductNameUpdateRequest request) {
        return updateProductNameUseCase.updateProductName(franchiseId, branchId, productId, request.getNewName())
                .map(FranchiseResponse::fromDomain);
    }
}