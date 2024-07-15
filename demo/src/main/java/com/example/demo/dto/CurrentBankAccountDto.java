package com.example.demo.dto;

import java.util.Date;

import com.example.demo.model.AccountStatus;

public record CurrentBankAccountDto(
    String id,
    double balance,
    Date createdAt,
    AccountStatus status,
    CustomerDto customerDTO,
    double overDraft
) {
    
}
