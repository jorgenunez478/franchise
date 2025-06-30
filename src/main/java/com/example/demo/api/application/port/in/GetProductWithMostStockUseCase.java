package com.example.demo.api.application.port.in;

import com.example.demo.api.domain.model.Product;
import reactor.core.publisher.Flux;

import java.util.Map;

public interface GetProductWithMostStockUseCase {
    /**
     * Obtiene el producto con más stock por sucursal para una franquicia puntual.
     * @param franchiseId El ID de la franquicia.
     * @return Un Flux que emite un mapa donde la clave es el nombre de la sucursal y el valor es el producto con más stock.
     */
    Flux<Map.Entry<String, Product>> getProductWithMostStockByBranch(String franchiseId);
}