package com.example.demo.api.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Branch {
    private String id;
    private String name;
    @Singular
    private List<Product> products;

    public Branch(String name, List<Product> products) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.products = products != null ? products : List.of();
    }

    public Branch(String id, String name, List<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products != null ? products : List.of();
    }

    public void addProduct(Product product) {
        if (this.products == null) {
            this.products = new java.util.ArrayList<>();
        }
        this.products.add(product);
    }

    public void removeProduct(String productId) {
        if (this.products != null) {
            this.products.removeIf(p -> p.getId().equals(productId));
        }
    }

    public Product getProductByName(String productName) {
        return this.products.stream()
                .filter(p -> p.getName().equals(productName))
                .findFirst()
                .orElse(null);
    }

    public Product getProductById(String productId) {
        return this.products.stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    public Product getProductWithMostStock() {
        if (products == null || products.isEmpty()) {
            return null;
        }
        return products.stream()
                .max(java.util.Comparator.comparingInt(Product::getStock))
                .orElse(null);
    }
}