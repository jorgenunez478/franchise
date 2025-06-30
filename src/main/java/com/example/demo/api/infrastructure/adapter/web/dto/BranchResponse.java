package com.example.demo.api.infrastructure.adapter.web.dto;

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
public class BranchResponse {
    private String id;
    private String name;
    private List<ProductResponse> products;

    public static BranchResponse fromDomain(Branch branch) {
        return BranchResponse.builder()
                .id(branch.getId())
                .name(branch.getName())
                .products(branch.getProducts().stream()
                        .map(ProductResponse::fromDomain)
                        .collect(Collectors.toList()))
                .build();
    }
}