package com.example.demo.api.infrastructure.adapter.web.dto;

import com.example.demo.api.domain.model.Franchise;
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
public class FranchiseResponse {
    private String id;
    private String name;
    private List<BranchResponse> branches;

    public static FranchiseResponse fromDomain(Franchise franchise) {
        return FranchiseResponse.builder()
                .id(franchise.getId())
                .name(franchise.getName())
                .branches(franchise.getBranches().stream()
                        .map(BranchResponse::fromDomain)
                        .collect(Collectors.toList()))
                .build();
    }
}