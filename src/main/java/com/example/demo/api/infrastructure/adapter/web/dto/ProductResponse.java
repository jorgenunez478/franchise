package com.example.demo.api.infrastructure.adapter.web.dto;

import com.example.demo.api.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private String id;
    private String name;
    private int stock;

    public static ProductResponse fromDomain(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .stock(product.getStock())
                .build();
    }
}