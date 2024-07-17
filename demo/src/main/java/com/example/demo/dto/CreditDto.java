package com.example.demo.dto;

public record CreditDto(
    String accountId,
    double amount,
    String description
) {
    
}
