package com.micro.cors.services;

import java.time.LocalDate;
import java.util.List;

import com.micro.cors.dao.LoanStatus;
import com.micro.cors.dao.SimpleLoanDTO;
import com.micro.cors.entities.Loan;

public interface LoanService {
 public List<Loan> findAllLoansByEndDateBefore(LocalDate maxEndDate);
    
    public List<Loan> getAllOpenLoansOfThisCustomer(String email, LoanStatus status);
    
    public Loan getOpenedLoan(SimpleLoanDTO simpleLoanDTO);
    
    public boolean checkIfLoanExists(SimpleLoanDTO simpleLoanDTO);
    
    public Loan saveLoan(Loan loan);
    
    public void closeLoan(Loan loan);
}
