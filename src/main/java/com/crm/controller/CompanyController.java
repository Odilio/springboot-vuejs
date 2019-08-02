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
import com.crm.model.Question;
import com.crm.repository.CompanyRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Company")
@RestController
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @ApiOperation(value = "Mostra lista de usuarios")
    @GetMapping("/companys")
    public List<Company> getCompanys() {
        return companyRepository.findAll();
    }

    @GetMapping(path = "/companys/paged")
    public List<Company> getAllCompanyByPage(@RequestParam("page") int pageIndex, 
            @RequestParam("size") int pageSize){
    	return companyRepository
    			.findAllPage(PageRequest.of(pageIndex, pageSize)).getContent();
    }

    @PostMapping("/companys")
    public Company createCompany(@Valid @RequestBody Company Company) {
        return companyRepository.save(Company);
    }

    @PutMapping("/companys/{CompanyId}")
    public Company updateCompany(@PathVariable Long CompanyId,
                                   @Valid @RequestBody Company CompanyRequest) {
        return companyRepository.findById(CompanyId)
                .map(Company -> {
                    Company.setName(CompanyRequest.getName());
                    Company.setEmail(CompanyRequest.getEmail());
                    return companyRepository.save(Company);
                }).orElseThrow(() -> new ResourceNotFoundException("Company not found with id " + CompanyId));
    }


    @DeleteMapping("/companys/{CompanyId}")
    public ResponseEntity<?> deleteCompany(@PathVariable Long CompanyId) {
        return companyRepository.findById(CompanyId)
                .map(Company -> {
                	companyRepository.delete(Company);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Company not found with id " + CompanyId));
    }
}