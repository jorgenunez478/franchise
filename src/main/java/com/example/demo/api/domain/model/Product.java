package com.example.demo.api.domain.model;

import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@Builder
public class Product {
    private String id;
    private String name;
    private int stock;

    public Product(String name, int stock) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.stock = stock;
    }

    public Product(String id, String name, int stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    public void updateStock(int newStock) {
        if (newStock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
        this.stock = newStock;
    }
}