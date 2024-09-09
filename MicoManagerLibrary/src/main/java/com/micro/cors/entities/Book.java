package com.micro.cors.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name =" book")
@Data
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable= false)
    private String title;
    
	@Column(nullable=false, unique=true)
    private String isbn;
    
    private LocalDate releaseDate;
    
    @Column(nullable=false)
    private LocalDate registerDate;
    
    private Integer totalExamplaries;
    
    private String author;
    
    @ManyToOne(optional=false)
    @JoinTable(name="catCode" )
    private Category category;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "LOAN.book", cascade = CascadeType.ALL)
    Set<Loan> loans = new HashSet<Loan>();

}
