package com.micro.cors.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.cors.entities.Customer;
import com.micro.cors.reposiry.CustomerRepo;

@Service
public class CustomerServiceImple implements CustomerService{

	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public Customer saveCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	@Override
	public Customer findByEmail(String email) {
		return customerRepo.findByEmail(email);
	}

	@Override
	public boolean checkIfExistById(int id) {
		return customerRepo.existsById(id);
	}

	@Override
	public void deleteCustomer(int customerId) {
		customerRepo.deleteById(customerId);
	}

	@Override
	public Customer findById(int id) {
		return customerRepo.findById(id);
	}
}
