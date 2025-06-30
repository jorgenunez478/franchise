package com.example.demo.api.infrastructure.adapter.web.dto;

import com.example.demo.api.domain.model.Franchise;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FranchiseRequest {
    @NotBlank(message = "El nombre de la franquicia es requerido")
    private String name;

    public Franchise toDomain() {
        return new Franchise(this.name, null); // Las branches se añadirán después
    }
}