package com.example.demo.api.application.port.in;

import com.example.demo.api.domain.model.Franchise;
import reactor.core.publisher.Mono;

public interface DeleteProductFromBranchUseCase {
    /**
     * Elimina un producto de una sucursal específica dentro de una franquicia.
     * @param franchiseId El ID de la franquicia.
     * @param branchId El ID de la sucursal.
     * @param productId El ID del producto a eliminar.
     * @return Un Mono que emite la franquicia actualizada.
     */
    Mono<Franchise> deleteProductFromBranch(String franchiseId, String branchId, String productId);
}