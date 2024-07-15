package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.BankAccountDto;
import com.example.demo.dto.CurrentBankAccountDto;
import com.example.demo.dto.CustomerDto;
import com.example.demo.dto.SavingBankAccountDto;
import com.example.demo.exceptions.BalanceNotSufficientException;
import com.example.demo.exceptions.BankAccountNotFoundException;
import com.example.demo.exceptions.CustomerNotFoundException;

public interface BankAccountService {
    CustomerDto saveCustomer(CustomerDto customerDTO);

    CurrentBankAccountDto saveCurrentBankAccount(double initialBalance, double overDraft, String customerId) throws CustomerNotFoundException;
    
    SavingBankAccountDto saveSavingBankAccount(double initialBalance, double interestRate, String customerId) throws CustomerNotFoundException;
    
    List<CustomerDto> listCustomers();
    
    BankAccountDto getBankAccount(String accountId) throws BankAccountNotFoundException;
    
    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    
    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
    
    void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;

    List<BankAccountDto> bankAccountList();

    CustomerDto getCustomer(String customerId) throws CustomerNotFoundException;

    CustomerDto updateCustomer(CustomerDto customerDTO);

    void deleteCustomer(String customerId);
}
