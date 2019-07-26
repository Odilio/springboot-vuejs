package com.crm.repository;
import org.springframework.data.jpa.repository.JpaRepository;  
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.crm.model.Todo;  
  
@RepositoryRestResource
public  
interface TodoRepository extends JpaRepository<Todo, Long> {}