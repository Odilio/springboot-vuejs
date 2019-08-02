package com.crm.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crm.model.Company;
import com.crm.model.Question;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	@Query("select c from Company c")
	List<Company> findAll();
	
	@Query("select c from Company c")
	Page<Company> findAllPage(Pageable pageable);
}