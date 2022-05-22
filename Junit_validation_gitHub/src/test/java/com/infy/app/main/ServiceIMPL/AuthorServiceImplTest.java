package com.infy.app.main.ServiceIMPL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.infy.app.main.Bean.Author;
import com.infy.app.main.Repository.AuthorRepository;

@SpringBootTest
class AuthorServiceImplTest {

	@Autowired
	private AuthorServiceImpl authorServiceImpl;
	
	@MockBean
	private AuthorRepository authorRepository;
	
	@Test
	final void testPostAllData() {
		Author author=Author.builder().id(25l).firstName("PoojaZ").lastName("Kherdikar").email("pk@gmail.com").build();
		Mockito.when(authorRepository.save(author)).thenReturn(author);
		
		Author actual=authorServiceImpl.postAllData(author);
		assertThat(actual).isEqualTo(author);
//		assertEquals(actual, author);
	}

	@Test
	final void testDelete() {
		Author author=Author.builder().firstName("PoojaZ").lastName("Kherdikar").email("pk@gmail.com").build();
		Mockito.when(authorRepository.save(author)).thenReturn(author);
		authorServiceImpl.delete(author.getId());
		
		Author actual=authorRepository.getById(author.getId());
		assertThat(actual).isNull();
	}
	
	@Test
	void testGetAllData()
	{
		Author author=Author.builder().firstName("PoojaZ").lastName("Kherdikar").email("pk@gmail.com").build();
		List<Author> l=new ArrayList<Author>();
		l.add(author);
		Mockito.when(authorRepository.findAll()).thenReturn(l);
		
		List<Author> actual=authorServiceImpl.getAllData();
		assertThat(actual).isEqualTo(l);
		
	}
}
