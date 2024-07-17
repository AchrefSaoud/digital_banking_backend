package com.example.demo.repositries;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Operation;

public interface OperationRepo extends JpaRepository<Operation,Long>{

    List<Operation> findByBankAccountId(String accountId);

    Page<Operation> findByBankAccountIdOrderByDateDesc(String accountId, PageRequest of);
    
}
