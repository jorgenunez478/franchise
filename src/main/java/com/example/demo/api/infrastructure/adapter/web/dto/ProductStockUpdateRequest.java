package com.example.demo.api.infrastructure.adapter.web.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockUpdateRequest {
    @Min(value = 0, message = "El nuevo stock no puede ser negativo")
    private int newStock;
}