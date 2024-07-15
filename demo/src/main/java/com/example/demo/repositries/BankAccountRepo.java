package com.example.demo.repositries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.BankAccount;

public interface BankAccountRepo extends JpaRepository<BankAccount,String>{
    
}
