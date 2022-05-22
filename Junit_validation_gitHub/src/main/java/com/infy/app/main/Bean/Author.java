package com.infy.app.main.Bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Author_Table")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Author {
	
	@Id
	@Column(name="author_id",unique = true,updatable= false, nullable=false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "First name is Mandetory")
	@Pattern(regexp = "^[A-Za-z]+$")
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@NotBlank(message = "Last name is Mandetory")
	@Pattern(regexp = "^[A-Za-z]+$")
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Please enter valid email id..")
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
//	private List<Book> book=new ArrayList<Book>();
	
}
