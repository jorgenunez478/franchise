package com.example.demo.api.infrastructure.adapter.web.dto;

import com.example.demo.api.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductWithBranchResponse {
    private String branchName;
    private ProductResponse product;

    public ProductWithBranchResponse(String branchName, Product product) {
        this.branchName = branchName;
        this.product = ProductResponse.fromDomain(product);
    }
}