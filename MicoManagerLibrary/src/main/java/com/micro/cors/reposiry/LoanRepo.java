package com.micro.cors.reposiry;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.micro.cors.dao.LoanStatus;
import com.micro.cors.entities.Loan;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface LoanRepo extends JpaRepository<Loan,Integer>{
	
	public List<Loan> findBybeginDate(LocalDate beginDate);
	
	@Query("select lo"
			+ "from Loan lo"
			+ "INNER JOIN lo.pk.customer cus"
			+ "where cus.email = ?1"
			+ "and lo.status = ?2")
	public List<Loan> getAllOpenLoanOfThisCustomer(String email, LoanStatus status);
	
	
	@Query("select lo "
			+ "from Loan lo"
			+ "INNER JOIN lo.pk.customer cus"
			+ "INNER JOIN lo.pk.book b"
			+ "WHERE b.id = ?1"
			+ "AND cus.id = ?2"
			+ "AND lo.status = ?3")
	public Loan getLoanByCriteria(Integer bookId, Integer customerId, LoanStatus status);
	
	@Query("select lo"
			+ "from Loan lo"
			+ "where upper(lo.endDate) = ?1")
	public List<Loan> findAllLoansByEndDateBefore(LocalDate maxEndDate);

}
