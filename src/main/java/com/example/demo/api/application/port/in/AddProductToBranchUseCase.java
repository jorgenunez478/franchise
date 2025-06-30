package com.example.demo.api.application.port.in;

import com.example.demo.api.domain.model.Franchise;
import com.example.demo.api.domain.model.Product;
import reactor.core.publisher.Mono;

public interface AddProductToBranchUseCase {
    /**
     * Agrega un nuevo producto a una sucursal específica dentro de una franquicia.
     * @param franchiseId El ID de la franquicia.
     * @param branchId El ID de la sucursal.
     * @param newProduct El nuevo producto a agregar.
     * @return Un Mono que emite la franquicia actualizada.
     */
    Mono<Franchise> addProductToBranch(String franchiseId, String branchId, Product newProduct);
}