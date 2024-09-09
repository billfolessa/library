package com.micro.cors.services;

import com.micro.cors.entities.Customer;

public interface CustomerService {

	public Customer saveCustomer(Customer customer); 
	
	public Customer findByEmail(String email);
	
	public Customer findById(int id);
	
	public void deleteCustomer(int customerId);
	
	public boolean checkIfExistById(int id);
}
