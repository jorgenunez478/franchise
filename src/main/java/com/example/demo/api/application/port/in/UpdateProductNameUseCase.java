package com.example.demo.api.application.port.in;

import com.example.demo.api.domain.model.Franchise;
import reactor.core.publisher.Mono;

public interface UpdateProductNameUseCase {
    /**
     * Actualiza el nombre de un producto específico dentro de una sucursal y franquicia.
     * @param franchiseId El ID de la franquicia.
     * @param branchId El ID de la sucursal.
     * @param productId El ID del producto a actualizar.
     * @param newName El nuevo nombre para el producto.
     * @return Un Mono que emite la franquicia actualizada.
     */
    Mono<Franchise> updateProductName(String franchiseId, String branchId, String productId, String newName);
}