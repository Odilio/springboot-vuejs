package com.crm.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crm.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	@Query("select c from Customer c  where c.id = :id")
	Customer findById(@Param("id") long id);
	
	@Query("select c from Customer c")
	List<Customer> findAll();
	
	
	@Query("select c from Customer c")
	Page<Customer> findAllPage(Pageable pageable);
}