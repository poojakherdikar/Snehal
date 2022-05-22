package com.infy.app.main.Controller;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
//import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.app.main.Bean.Author;
import com.infy.app.main.Exception.DuplicateEntityException;
import com.infy.app.main.ServiceIMPL.AuthorServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
class AuthorControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private AuthorServiceImpl authorServiceImpl;
	
	@Test
	public void testPostDataCreatedStatus200() throws Exception {
		Author au=new Author(10l, "Ajinkya", "Gaikwad", "ajinkya2962@gamail.com");
		Mockito.when(authorServiceImpl.postAllData(au)).thenReturn(au);
		
		String json=objectMapper.writeValueAsString(au);
		this.mockMvc.perform(
								post("/post").
								contentType("Application/json").
								content(json)
							).andExpect(status().isCreated());
	}
	
	@Test
	public void testPostDataCreatedStatus400() throws Exception {
		
		Author au= Author.builder().lastName("Kher45dikarDD").email("poojagmailcom").build();
		String json=objectMapper.writeValueAsString(au);
		
		this.mockMvc.perform(
							 post("/post")
							.contentType("application/json")
							.content(json))
							.andExpect(status().isBadRequest())
//							.andExpect(jsonPath("$.timestamp", is(notNullValue())))
//							.andExpect(jsonPath("$.errors").isArray())
//							.andExpect(jsonPath("$.errors", hasSize(3)))
//							.andExpect(jsonPath("$.errors",  hasItem("A valid mail address is mandatory")))
//							.andExpect(jsonPath("$.errors",  hasItem("First name is mandatory")))
//							.andExpect(jsonPath("$.errors", hasItem("last name is mandatory")))
							;								 
	}
	
	@Test
	public void TestPostDataCreatedStatus404() throws Exception
	{
		long existingAuthorId=0L;
		Author auth=Author.builder().id(existingAuthorId).firstName("Pooja").lastName("Kherdikar").email("pk@gmail.com").build();
//		Author auth= new Author(10l, "Ajinkya", "Gaikwad", "ajinkya@gmail.com");
		System.out.println(auth);
		when(this.authorServiceImpl.postAllData(auth)).thenThrow(new DuplicateEntityException());
		String json=objectMapper.writeValueAsString(auth);
		this.mockMvc.perform(
				post("/post")
				.contentType("application/json")
				.content(json)
				)
				.andExpect(status().isUnprocessableEntity());
	}
	
//	@Test
//	final void testDeleteData() {
//		fail("Not yet implemented"); // TODO
//	}
}
