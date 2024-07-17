package com.example.demo.dto;

import java.util.Date;

import com.example.demo.model.OperationType;

public record OperationDto(
    Long id,
    Date operationDate,
    double amount,
    OperationType type,
    String description
) {
}
