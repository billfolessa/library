package com.micro.cors.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Embeddable
@Data
public class LoanId implements Serializable{
	private static final long serialVersionUID = 3912193101593832821L;

	@ManyToOne
	@JoinColumn(name = "BOOK_ID")
    private Book book;
	@ManyToOne
	@JoinColumn(name="CUSTOMER_ID")
    private Customer customer;
    
	@Column(name="creationDateTime")
    private LocalDateTime creationDateTime;
}
