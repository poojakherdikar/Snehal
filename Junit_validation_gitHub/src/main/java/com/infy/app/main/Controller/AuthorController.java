package com.infy.app.main.Controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.infy.app.main.Bean.Author;
import com.infy.app.main.Service.AuthorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
//@RequestMapping(path="/api/author")
@Slf4j
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;
	
	@PostMapping(path="/post",consumes={"application/json"})
	public ResponseEntity<?> postData(@Valid @RequestBody Author a)
	{
		log.info("Author post method started");
		Author at=authorService.postAllData(a);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(at.getId()).toUri();
		return ResponseEntity.created(location).build();
		//return new ResponseEntity<>(at,HttpStatus.CREATED);
	}
	
	@DeleteMapping(path="/delete", consumes = {"application/json"})
	public ResponseEntity<?> deleteData(@PathVariable("id") Long author_id)
	{
		log.info("inside author delete method");
		authorService.delete(author_id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value="/get", produces = {"application/json"})
	public ResponseEntity<List<Author>> getData()
	{
		log.info("get controller");
		List<Author> lst=authorService.getAllData();
		return new ResponseEntity<List<Author>>(lst, HttpStatus.OK);
	}
	
	@GetMapping (path="/singleGet/{firstName}", produces = {"application/json"})
	public ResponseEntity<Author> getByName(@PathVariable("firstName") String name)
	{
		String nameReturn=authorService.findByNameController(name);
		return new ResponseEntity<Author>(HttpStatus.OK);
	}
	
}
