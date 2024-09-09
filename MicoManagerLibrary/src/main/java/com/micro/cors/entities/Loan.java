package com.micro.cors.entities;

import java.io.Serializable;
import java.time.LocalDate;

import com.micro.cors.dao.LoanStatus;

import jakarta.persistence.AssociationOverride;
import jakarta.persistence.AssociationOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "LOAN")
//@AssociationOverrides({
//@AssociationOverride(name = "pk.book", joinColumns = @JoinColumn(name = "BOOK_ID")),
//@AssociationOverride(name = "pk.customer", joinColumns = @JoinColumn(name = "CUSTOMER_ID"))
//})
public class Loan implements Serializable  {
	
	private static final long serialVersionUID = 144293603488149743L;
   
	@EmbeddedId
    private LoanId pk = new LoanId();
    
	@Column(nullable = false)
    private LocalDate beginDate;
    
    private LocalDate endDate;
    
    @Enumerated(EnumType.STRING)
    @Column(name="STATUS")
    private LoanStatus status;

}
