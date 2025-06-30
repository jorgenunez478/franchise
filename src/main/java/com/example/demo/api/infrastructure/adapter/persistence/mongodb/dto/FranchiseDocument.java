package com.example.demo.api.infrastructure.adapter.persistence.mongodb.dto;

import com.example.demo.api.domain.model.Branch;
import com.example.demo.api.domain.model.Franchise;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "franchises")
public class FranchiseDocument {
    @Id
    private String id;
    private String name;
    private List<BranchDocument> branches;

    // Métodos de mapeo (pueden estar en un Mapper dedicado si se vuelven complejos)
    public static FranchiseDocument fromDomain(Franchise franchise) {
        return FranchiseDocument.builder()
                .id(franchise.getId())
                .name(franchise.getName())
                .branches(franchise.getBranches().stream()
                        .map(BranchDocument::fromDomain)
                        .collect(Collectors.toList()))
                .build();
    }

    public Franchise toDomain() {
        return new Franchise(
                this.id,
                this.name,
                this.branches.stream()
                        .map(BranchDocument::toDomain)
                        .collect(Collectors.toList())
        );
    }
}