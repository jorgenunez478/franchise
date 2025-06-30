package com.example.demo.api.infrastructure.adapter.persistence.mongodb.dto;

import com.example.demo.api.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDocument {
    private String id;
    private String name;
    private int stock;

    /**
     * Convierte un objeto de dominio Product a un ProductDocument.
     * @param product El objeto de dominio Product.
     * @return El ProductDocument correspondiente.
     */
    public static ProductDocument fromDomain(Product product) {
        return ProductDocument.builder()
                .id(product.getId())
                .name(product.getName())
                .stock(product.getStock())
                .build();
    }

    /**
     * Convierte un ProductDocument a un objeto de dominio Product.
     * @return El objeto de dominio Product correspondiente.
     */
    public Product toDomain() {
        return new Product(
                this.id,
                this.name,
                this.stock
        );
    }
}