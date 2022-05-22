package com.infy.app.main.Repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.infy.app.main.Bean.Author;

@SpringBootTest
class AuthorRepositoryTest {
	
	@Autowired
	private AuthorRepository authorRepository;

	@Test
	final void testSave() {
		Author a=Author.builder().firstName("Pooja").lastName("Khe").email("p@gmail.com").build();
		Author actual=authorRepository.save(a);
		assertEquals(actual, a);
		
		authorRepository.delete(actual);	
	}
	
	@Test
	void testDelete()
	{
		Author a=Author.builder().firstName("Pooja").lastName("Khe").email("p@gmail.com").build();
		Author au = authorRepository.save(a);
		authorRepository.delete(a);
		Optional<Author> optional = authorRepository.findById(au.getId());
		assertThat(optional).isEmpty();
		
	}
	
	@Test
	void testGetAllData()
	{
		Author a=Author.builder().firstName("Pooja").lastName("Khe").email("p@gmail.com").build();
		authorRepository.save(a);
//		List<Author> list= new ArrayList<Author>();
//		list.add(a);
//		
		List<Author> listActual = authorRepository.findAll();
//		assertThat(listActual).isEqualTo(list);
		
		Author forloopActual=null;
		for(Author au :listActual) 
		{
			if(au.equals(a))
			{
				forloopActual=au;
			}
		}
		assertEquals(forloopActual, a);
		
		authorRepository.deleteAll();
		
	}

}
