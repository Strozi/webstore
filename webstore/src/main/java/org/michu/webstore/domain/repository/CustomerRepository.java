package org.michu.webstore.domain.repository;

import java.util.List;

import org.michu.webstore.domain.Customer;


public interface CustomerRepository {
	
	List <Customer> getAllCustomers(); 
	Customer getCustomerById(String customerId);

}
