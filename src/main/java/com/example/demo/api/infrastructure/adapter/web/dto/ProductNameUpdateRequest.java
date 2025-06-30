package com.example.demo.api.infrastructure.adapter.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductNameUpdateRequest {
    @NotBlank(message = "El nuevo nombre del producto es requerido")
    private String newName;
}