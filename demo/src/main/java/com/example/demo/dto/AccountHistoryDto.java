package com.example.demo.dto;

import java.util.List;

public record AccountHistoryDto(
    String accountId,
    double balance,
    int currentPage,
    int totalPages,
    int pageSize,
    List<OperationDto> accountOperationDTOS
) {
    
}
