package com.example.demo.api.infrastructure.adapter.persistence.mongodb.dto;

import com.example.demo.api.domain.model.Branch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BranchDocument {
    private String id;
    private String name;
    private List<ProductDocument> products;

    public static BranchDocument fromDomain(Branch branch) {
        return BranchDocument.builder()
                .id(branch.getId())
                .name(branch.getName())
                .products(branch.getProducts().stream()
                        .map(ProductDocument::fromDomain)
                        .collect(Collectors.toList()))
                .build();
    }

    public Branch toDomain() {
        return new Branch(
                this.id,
                this.name,
                this.products.stream()
                        .map(ProductDocument::toDomain)
                        .collect(Collectors.toList())
        );
    }
}