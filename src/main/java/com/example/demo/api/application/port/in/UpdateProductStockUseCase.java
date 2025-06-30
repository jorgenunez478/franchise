package com.example.demo.api.application.port.in;

import com.example.demo.api.domain.model.Franchise;
import reactor.core.publisher.Mono;

public interface UpdateProductStockUseCase {
    /**
     * Modifica el stock de un producto específico dentro de una sucursal y franquicia.
     * @param franchiseId El ID de la franquicia.
     * @param branchId El ID de la sucursal.
     * @param productId El ID del producto cuyo stock se va a modificar.
     * @param newStock La nueva cantidad de stock.
     * @return Un Mono que emite la franquicia actualizada.
     */
    Mono<Franchise> updateProductStock(String franchiseId, String branchId, String productId, int newStock);
}