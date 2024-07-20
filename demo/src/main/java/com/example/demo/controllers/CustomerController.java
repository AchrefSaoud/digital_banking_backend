package com.example.demo.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CustomerDto;
import com.example.demo.exceptions.CustomerNotFoundException;
import com.example.demo.services.BankAccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@Tag(name = "Customer Management", description = "Operations pertaining to customer management")
public class CustomerController {
    
    private BankAccountService bankAccountService;

    @Operation(summary = "Get all customers", description = "Retrieve a list of all customers")
    @GetMapping("/customers")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<CustomerDto> customers(){
        return bankAccountService.listCustomers();
    }

    @Operation(summary = "Search customers", description = "Search for customers based on a keyword")
    @GetMapping("/customers/search")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<CustomerDto> searchCustomers(
            @Parameter(description = "The keyword to search for in customer names", example = "John") 
            @RequestParam(name = "keyword", defaultValue = "") String keyword){
        return bankAccountService.searchCustomers("%"+keyword+"%");
    }

    @Operation(summary = "Get customer by ID", description = "Retrieve a customer by their ID")
    @GetMapping("/customers/{id}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public CustomerDto getCustomer(
            @Parameter(description = "The ID of the customer to retrieve", example = "123") 
            @PathVariable(name = "id") String customerId) throws CustomerNotFoundException {
        return bankAccountService.getCustomer(customerId);
    }

    @Operation(summary = "Save customer", description = "Save a new customer")
    @PostMapping("/customers")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public CustomerDto saveCustomer(
            @Parameter(description = "The customer data to be saved") 
            @RequestBody CustomerDto customerDTO){
        return bankAccountService.saveCustomer(customerDTO);
    }

    @Operation(summary = "Update customer", description = "Update an existing customer")
    @PutMapping("/customers/{customerId}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public CustomerDto updateCustomer(
            @Parameter(description = "The ID of the customer to update", example = "123") 
            @PathVariable String customerId, 
            @Parameter(description = "The updated customer data") 
            @RequestBody CustomerDto customerDTO){
        CustomerDto updatedCustomerDTO = new CustomerDto(customerId, customerDTO.name(), customerDTO.email());
        return bankAccountService.updateCustomer(updatedCustomerDTO);
    }

    @Operation(summary = "Delete customer", description = "Delete a customer by their ID")
    @DeleteMapping("/customers/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public void deleteCustomer(
            @Parameter(description = "The ID of the customer to delete", example = "123") 
            @PathVariable String id){
        bankAccountService.deleteCustomer(id);
    }
}
