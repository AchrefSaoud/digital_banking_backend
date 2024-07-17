package com.example.demo.dto;

public record TransferRequestDto(
    String accountSource,
    String accountDestination,
    double amount,
    String description
) {
    
}
