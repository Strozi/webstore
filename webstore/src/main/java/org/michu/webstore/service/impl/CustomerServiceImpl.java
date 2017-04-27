package org.michu.webstore.service.impl;

import java.util.List;

import org.michu.webstore.domain.Customer;
import org.michu.webstore.domain.repository.CustomerRepository;
import org.michu.webstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public List<Customer> getAllCustomers() {		
		return customerRepository.getAllCustomers();
	}
	
}
