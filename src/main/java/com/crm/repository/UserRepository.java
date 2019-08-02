package com.crm.repository;
import com.crm.model.Customer;
import com.crm.model.User;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("select c from User c  where c.id = :id")
	User findById(@Param("id") long id);
	
	@Query("select c from User c")
	List<User> findAll();
	
	
	@Query("select c from User c")
	Page<User> findAllPage(Pageable pageable);
}