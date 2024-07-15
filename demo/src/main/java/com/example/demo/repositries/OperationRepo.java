package com.example.demo.repositries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Operation;

public interface OperationRepo extends JpaRepository<Operation,Long>{
    
}
