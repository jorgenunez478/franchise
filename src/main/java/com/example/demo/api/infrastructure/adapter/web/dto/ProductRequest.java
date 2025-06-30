package com.example.demo.api.infrastructure.adapter.web.dto;

import com.example.demo.api.domain.model.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    @NotBlank(message = "El nombre del producto es requerido")
    private String name;
    @Min(value = 0, message = "El stock no puede ser negativo")
    private int stock;

    public Product toDomain() {
        return new Product(this.name, this.stock);
    }
}