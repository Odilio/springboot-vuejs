package com.crm.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crm.model.Question;

public interface QuestionRepository extends CrudRepository<Question, Long> {
	
	@Query("select c from Question c")
	List<Question> findAllSorted(Sort sort);
	
	@Query("select c from Question c")
	List<Question> findAll();
	
	@Query("select c from Question c")
	Page<Question> findAllPage(Pageable pageable);
}