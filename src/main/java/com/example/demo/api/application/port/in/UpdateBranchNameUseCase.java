package com.example.demo.api.application.port.in;

import com.example.demo.api.domain.model.Franchise;
import reactor.core.publisher.Mono;

public interface UpdateBranchNameUseCase {
    /**
     * Actualiza el nombre de una sucursal específica dentro de una franquicia.
     * @param franchiseId El ID de la franquicia.
     * @param branchId El ID de la sucursal a actualizar.
     * @param newName El nuevo nombre para la sucursal.
     * @return Un Mono que emite la franquicia actualizada.
     */
    Mono<Franchise> updateBranchName(String franchiseId, String branchId, String newName);
}