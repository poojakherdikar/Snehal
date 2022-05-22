package com.infy.app.main.Bean;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ManyToAny;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name="Book_Table")
public class Book {
	
	@Id
	@Column(name="Book_id", nullable = false, unique = true, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank (message = "mandetory")
	@Column(name = "book_title", nullable = false, updatable = true)
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@NotBlank (message = "mandetory")
	@Column(name = "genre", nullable = false)
	private String genre;

	@Column(name = "price")
	private BigDecimal price;
	
	@NotBlank (message = "mandetory")
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	private Author author;

}
