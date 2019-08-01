package com.crm;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import com.crm.repository.QuestionRepository;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

@DataJpaTest
public class QuestionRepositoryTest {

	@Autowired
	private QuestionRepository characterRepository;


	@Test
	public void returnsPage() {
		// database is initialized with script "data.sql"
		assertThat(
				characterRepository
						.findAllPage(PageRequest.of(0, 10))
						.getContent()
						.size())
				.isEqualTo(10);

	}

	

}