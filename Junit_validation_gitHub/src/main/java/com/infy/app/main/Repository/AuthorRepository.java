package com.infy.app.main.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infy.app.main.Bean.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{

	@Transactional
	String findByFirstName(String name);
	
	

}
