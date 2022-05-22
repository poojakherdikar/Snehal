package com.infy.app.main.Service;

import java.util.List;

import javax.validation.Valid;

import com.infy.app.main.Bean.Author;

public interface AuthorService {

	public void delete(Long author_id);

	public Author postAllData(@Valid Author a);

	public List<Author> getAllData();

	public String findByNameController(String name);
	
}
