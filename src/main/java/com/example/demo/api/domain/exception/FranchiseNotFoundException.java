package com.example.demo.api.domain.exception;

public class FranchiseNotFoundException extends RuntimeException {
    public FranchiseNotFoundException(String id) {
        super("Franquicia con ID " + id + " no encontrada.");
    }
}