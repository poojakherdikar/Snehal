package com.infy.app.main.ServiceIMPL;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.app.main.Bean.Author;
import com.infy.app.main.Exception.DuplicateEntityException;
import com.infy.app.main.Repository.AuthorRepository;
import com.infy.app.main.Service.AuthorService;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthorServiceImpl implements AuthorService {

	private final AuthorRepository authorRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public AuthorServiceImpl(AuthorRepository authorRepository1, ModelMapper modelMapper1) {
		this.authorRepository = authorRepository1;
		this.modelMapper=modelMapper1;
	}

	@Override
	@Transactional
	public Author postAllData(Author a) {
		log.info("*********** Inside service post method");
		if (a.getId() != null) 
		{
			log.warn("********** Possibility for dublicate");
			authorRepository.findById(a.getId()).ifPresent(author -> {
				throw new DuplicateEntityException("The entry with author with id - " + a.getId() + " is present");
			});
		}
//		Author at=modelMapper.map(a, Author.class);
		Author save = authorRepository.save(a);
		log.info("********** End of service leyer");
		return save;
	}

	@Override
	public void delete(Long author_id) {
		authorRepository.deleteById(author_id);
	}

	@Override
	public List<Author> getAllData() {
		List<Author> lst=authorRepository.findAll();
		return lst;
	}

	@Override
	public String findByNameController(String name) {
		String returnName=authorRepository.findByFirstName(name);
		return returnName;
	}

}
