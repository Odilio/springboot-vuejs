package com.crm.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crm.exception.ResourceNotFoundException;
import com.crm.model.Company;
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
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomersById(@PathVariable("id") long id) {
        return customerRepository.findById(id);
    }

    
    @GetMapping(path = "/customers/paged")
    public List<Customer> getAllCompanyByPage(@RequestParam("page") int pageIndex, 
            @RequestParam("size") int pageSize){
    	return customerRepository
    			.findAllPage(PageRequest.of(pageIndex, pageSize)).getContent();
    }
    
    @PostMapping("/customers")
    public Customer createCustomer(@Valid @RequestBody Customer Customer) {
        return customerRepository.save(Customer);
    }

    @PutMapping("/customers/{id}")
    public Customer updateCustomer(@PathVariable Long id,
                                   @Valid @RequestBody Customer CustomerRequest) {
        return customerRepository.findById(id)
                .map(Customer -> {
                    Customer.setName(CustomerRequest.getName());
                    Customer.setEmail(CustomerRequest.getEmail());
                    return customerRepository.save(Customer);
                }).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
    }


    @DeleteMapping("/customers/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        return customerRepository.findById(id)
                .map(Customer -> {
                	customerRepository.delete(Customer);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
    }
}