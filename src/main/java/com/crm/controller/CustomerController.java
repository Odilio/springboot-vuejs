package com.crm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crm.exception.ResourceNotFoundException;
import com.crm.model.Customer;
import com.crm.repository.CustomerRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Customer")
@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @ApiOperation(value = "Mostra lista de usuarios")
    @GetMapping("/customers")
    public Page<Customer> getCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }


    @PostMapping("/Customers")
    public Customer createCustomer(@Valid @RequestBody Customer Customer) {
        return customerRepository.save(Customer);
    }

    @PutMapping("/Customers/{CustomerId}")
    public Customer updateCustomer(@PathVariable Long CustomerId,
                                   @Valid @RequestBody Customer CustomerRequest) {
        return customerRepository.findById(CustomerId)
                .map(Customer -> {
                    Customer.setName(CustomerRequest.getName());
                    Customer.setEmail(CustomerRequest.getEmail());
                    return customerRepository.save(Customer);
                }).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + CustomerId));
    }


    @DeleteMapping("/Customers/{CustomerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long CustomerId) {
        return customerRepository.findById(CustomerId)
                .map(Customer -> {
                	customerRepository.delete(Customer);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + CustomerId));
    }
}