package com.example.demo.mapper;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.demo.dto.*;
import com.example.demo.model.*;

@Service
public class BankAccountMapperImpl {

    public CustomerDto fromCustomer(Customer customer) {
        if (customer == null) return null;
        return new CustomerDto(customer.getId(), customer.getName(), customer.getEmail());
    }

    public Customer fromCustomerDTO(CustomerDto customerDto) {
        if (customerDto == null) return null;
        Customer customer = new Customer();
        customer.setId(customerDto.id());
        customer.setName(customerDto.name());
        customer.setEmail(customerDto.email());
        return customer;
    }

    public SavingBankAccountDto fromSavingBankAccount(SavingAccount savingAccount) {
        if (savingAccount == null) return null;
        return new SavingBankAccountDto(
            savingAccount.getId(),
            savingAccount.getBalance(),
            savingAccount.getCreatedAt(),
            savingAccount.getStatus(),
            fromCustomer(savingAccount.getCustomer()),
            savingAccount.getInterestRate()
        );
    }
    public BankAccountDto fromSavingBankAccounttoBankAccountDto(SavingAccount savingAccount) {
        if (savingAccount == null) return null;
        return new BankAccountDto(
            "Saving account",
            savingAccount.getId()
        );
    }

    public SavingAccount fromSavingBankAccountDTO(SavingBankAccountDto savingBankAccountDto) {
        if (savingBankAccountDto == null) return null;
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setId(savingBankAccountDto.id());
        savingAccount.setBalance(savingBankAccountDto.balance());
        savingAccount.setCreatedAt(savingBankAccountDto.createdAt());
        savingAccount.setStatus(savingBankAccountDto.status());
        savingAccount.setCustomer(fromCustomerDTO(savingBankAccountDto.customerDTO()));
        savingAccount.setInterestRate(savingBankAccountDto.interestRate());
        return savingAccount;
    }

    public CurrentBankAccountDto fromCurrentBankAccount(CurrentAccount currentAccount) {
        if (currentAccount == null) return null;
        return new CurrentBankAccountDto(
            currentAccount.getId(),
            currentAccount.getBalance(),
            currentAccount.getCreatedAt(),
            currentAccount.getStatus(),
            fromCustomer(currentAccount.getCustomer()),
            currentAccount.getOverDraft()
        );
    }

    public BankAccountDto fromCurrentBankAccounttoBankAccountDto(CurrentAccount currentAccount) {
        if (currentAccount == null) return null;
        return new BankAccountDto(
            "Current Account",
            currentAccount.getId()
        );
    }

    public CurrentAccount fromCurrentBankAccountDTO(CurrentBankAccountDto currentBankAccountDto) {
        if (currentBankAccountDto == null) return null;
        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setId(currentBankAccountDto.id());
        currentAccount.setBalance(currentBankAccountDto.balance());
        currentAccount.setCreatedAt(currentBankAccountDto.createdAt());
        currentAccount.setStatus(currentBankAccountDto.status());
        currentAccount.setCustomer(fromCustomerDTO(currentBankAccountDto.customerDTO()));
        currentAccount.setOverDraft(currentBankAccountDto.overDraft());
        return currentAccount;
    }

    public OperationDto fromAccountOperation(Operation accountOperation) {
        Long id = accountOperation.getId();
        Date operationDate = accountOperation.getDate();
        double amount = accountOperation.getAmount();
        OperationType type = accountOperation.getType();
        String description = accountOperation.getDescription();
    
        return new OperationDto(id, operationDate, amount, type, description);
    }
    

}
