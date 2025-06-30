package com.example.demo.api.infrastructure.adapter.web.dto;

import com.example.demo.api.domain.model.Branch;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchRequest {
    @NotBlank(message = "El nombre de la sucursal es requerido")
    private String name;

    public Branch toDomain() {
        return new Branch(this.name, null); // Los productos se añadirán después
    }
}