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
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Customer {

	   @Id
	   @GeneratedValue(strategy = GenerationType.AUTO)
	   private Integer id;
	    
	   @Column(nullable =false)
	    private String firstName;
	    
	    private String lastName;
	    
	    private String job;
	    
	    private String address;
	    
	    @Column(nullable = false, unique = true)
	    private String email;
	    
	    private LocalDate creationDate;
	    
	    @OneToMany(fetch = FetchType.LAZY, mappedBy ="LOAN.customer", cascade = CascadeType.ALL)
	    Set<Loan> loans = new HashSet<Loan>();
}
