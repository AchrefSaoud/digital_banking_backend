package com.example.demo.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AccountHistoryDto;
import com.example.demo.dto.BankAccountDto;
import com.example.demo.dto.CreditDto;
import com.example.demo.dto.DebitDto;
import com.example.demo.dto.OperationDto;
import com.example.demo.dto.TransferRequestDto;
import com.example.demo.exceptions.BalanceNotSufficientException;
import com.example.demo.exceptions.BankAccountNotFoundException;
import com.example.demo.services.BankAccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@Tag(name = "Bank Account Management", description = "Operations pertaining to bank accounts")
public class BankAccountController {
    
    private BankAccountService bankAccountService;

    @Operation(summary = "Get bank account by ID", description = "Retrieve a bank account by its ID")
    @GetMapping("/accounts/{accountId}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public BankAccountDto getBankAccount(
            @Parameter(description = "The ID of the bank account", example = "123456") 
            @PathVariable String accountId) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(accountId);
    }

    @Operation(summary = "List all bank accounts", description = "Retrieve a list of all bank accounts")
    @GetMapping("/accounts")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<BankAccountDto> listAccounts(){
        return bankAccountService.bankAccountList();
    }

    @Operation(summary = "Get account operations history", description = "Retrieve operations history for a specific bank account")
    @GetMapping("/accounts/{accountId}/operations")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<OperationDto> getHistory(
            @Parameter(description = "The ID of the bank account", example = "123456") 
            @PathVariable String accountId){
        return bankAccountService.accountHistory(accountId);
    }

    @Operation(summary = "Get paginated account history", description = "Retrieve paginated operations history for a specific bank account")
    @GetMapping("/accounts/{accountId}/pageOperations")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public AccountHistoryDto getAccountHistory(
            @Parameter(description = "The ID of the bank account", example = "123456") 
            @PathVariable String accountId,
            @Parameter(description = "The page number to retrieve", example = "0") 
            @RequestParam(name="page", defaultValue = "0") int page,
            @Parameter(description = "The number of operations per page", example = "5") 
            @RequestParam(name="size", defaultValue = "5") int size) throws BankAccountNotFoundException {
        return bankAccountService.getAccountHistory(accountId, page, size);
    }

    @Operation(summary = "Debit from bank account", description = "Debit a specified amount from a bank account")
    @PostMapping("/accounts/debit")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public DebitDto debit(
            @Parameter(description = "The debit request data") 
            @RequestBody DebitDto debitDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        this.bankAccountService.debit(debitDTO.accountId(), debitDTO.amount(), debitDTO.description());
        return debitDTO;
    }

    @Operation(summary = "Credit to bank account", description = "Credit a specified amount to a bank account")
    @PostMapping("/accounts/credit")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public CreditDto credit(
            @Parameter(description = "The credit request data") 
            @RequestBody CreditDto creditDTO) throws BankAccountNotFoundException {
        this.bankAccountService.credit(creditDTO.accountId(), creditDTO.amount(), creditDTO.description());
        return creditDTO;
    }

    @Operation(summary = "Transfer between accounts", description = "Transfer a specified amount from one account to another")
    @PostMapping("/accounts/transfer")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public void transfer(
            @Parameter(description = "The transfer request data") 
            @RequestBody TransferRequestDto transferRequestDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        this.bankAccountService.transfer(
                transferRequestDTO.accountSource(),
                transferRequestDTO.accountDestination(),
                transferRequestDTO.amount());
    }
}
