package com.micro.cors.reposiry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.micro.cors.entities.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer>{

	public Customer findByEmail(String email);
	
	public Customer findById(int id);
}
