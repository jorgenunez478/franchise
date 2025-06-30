package com.example.demo.api.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Franchise {
    private String id; // Usaremos UUIDs como Strings para IDs
    private String name;
    @Singular
    private List<Branch> branches;

    // Constructor para cuando se crea una nueva franquicia sin ID
    public Franchise(String name, List<Branch> branches) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.branches = branches != null ? branches : List.of();
    }

    // Constructor completo para cuando se recupera de la persistencia
    public Franchise(String id, String name, List<Branch> branches) {
        this.id = id;
        this.name = name;
        this.branches = branches != null ? branches : List.of();
    }

    public void addBranch(Branch branch) {
        if (this.branches == null) {
            this.branches = new java.util.ArrayList<>();
        }
        this.branches.add(branch);
    }

    public Branch getBranchByName(String branchName) {
        return this.branches.stream()
                .filter(b -> b.getName().equals(branchName))
                .findFirst()
                .orElse(null);
    }

    public Product getProductWithMostStockInBranch(String branchId) {
        return this.branches.stream()
                .filter(b -> b.getId().equals(branchId))
                .findFirst()
                .map(Branch::getProductWithMostStock)
                .orElse(null);
    }
}