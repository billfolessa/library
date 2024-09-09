package com.micro.cors.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.cors.dao.LoanStatus;
import com.micro.cors.dao.SimpleLoanDTO;
import com.micro.cors.entities.Loan;
import com.micro.cors.reposiry.LoanRepo;

@Service
public class LoanServiceImple implements LoanService {
	
	@Autowired
	private LoanRepo loanRepo;

	@Override
	public List<Loan> findAllLoansByEndDateBefore(LocalDate maxEndDate) {
		return loanRepo.findAllLoansByEndDateBefore(maxEndDate);
	}

	@Override
	public List<Loan> getAllOpenLoansOfThisCustomer(String email, LoanStatus status) {
		return loanRepo.getAllOpenLoanOfThisCustomer(email, status);
	}

	@Override
	public Loan getOpenedLoan(SimpleLoanDTO simpleLoanDTO) {
		return loanRepo.getLoanByCriteria(simpleLoanDTO.getBookId(), simpleLoanDTO.getCustomerId(), LoanStatus.OPEN);
	}

	@Override
	public boolean checkIfLoanExists(SimpleLoanDTO simpleLoanDTO) {
		Loan loan = loanRepo.getLoanByCriteria(simpleLoanDTO.getBookId(), simpleLoanDTO.getCustomerId(), LoanStatus.OPEN);
        if(loan != null) {
            return true;
        }
        return false;
	}

	@Override
	public Loan saveLoan(Loan loan) {
		return loanRepo.save(loan);
	}

	@Override
	public void closeLoan(Loan loan) {
		loan.setStatus(LoanStatus.CLOSE);
		loanRepo.save(loan);
		// TODO Auto-generated method stub
		
	}

}
