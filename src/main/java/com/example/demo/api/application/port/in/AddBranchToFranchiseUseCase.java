package com.example.demo.api.application.port.in;

import com.example.demo.api.domain.model.Branch;
import com.example.demo.api.domain.model.Franchise;
import reactor.core.publisher.Mono;

public interface AddBranchToFranchiseUseCase {
    /**
     * Agrega una nueva sucursal a una franquicia existente.
     * @param franchiseId El ID de la franquicia a la que se agregará la sucursal.
     * @param newBranch La nueva sucursal a agregar.
     * @return Un Mono que emite la franquicia actualizada.
     */
    Mono<Franchise> addBranchToFranchise(String franchiseId, Branch newBranch);
}