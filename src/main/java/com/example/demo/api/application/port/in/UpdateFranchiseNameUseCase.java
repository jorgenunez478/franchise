package com.example.demo.api.application.port.in;

import com.example.demo.api.domain.model.Franchise;
import reactor.core.publisher.Mono;

public interface UpdateFranchiseNameUseCase {
    /**
     * Actualiza el nombre de una franquicia existente.
     * @param franchiseId El ID de la franquicia a actualizar.
     * @param newName El nuevo nombre para la franquicia.
     * @return Un Mono que emite la franquicia actualizada.
     */
    Mono<Franchise> updateFranchiseName(String franchiseId, String newName);
}