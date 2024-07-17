package com.example.demo.dto;

public record DebitDto(
    String accountId,
    double amount,
    String description
) {
    
}
