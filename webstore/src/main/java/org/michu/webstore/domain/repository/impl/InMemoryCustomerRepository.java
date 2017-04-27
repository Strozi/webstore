package org.michu.webstore.domain.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.michu.webstore.domain.Customer;
import org.michu.webstore.domain.Product;
import org.michu.webstore.domain.repository.CustomerRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository{

	private List<Customer> listOfCustomers = new ArrayList<>();
	
	public InMemoryCustomerRepository(){
		
		Customer Adam = new Customer("C1", "gracjan93", "Poznań 15");
		Adam.setNoOfOrdersMade(153);
		Customer Ola = new Customer("C1", "baśkaXD", "Warszawa 2c");
		Ola.setNoOfOrdersMade(14);
		Customer Jan = new Customer("C1", "derpminator", "Kraków 19");
		Jan.setNoOfOrdersMade(9999);
		
		listOfCustomers.add(Adam);
		listOfCustomers.add(Ola);
		listOfCustomers.add(Jan);
	}
	
	@Override
	public List<Customer> getAllCustomers() {
		
		
		return listOfCustomers;
	}

	@Override
	public Customer getCustomerById(String customerId) {
		
		Customer customerById = null;
		for(Customer customer : listOfCustomers){
			
			if(customer!=null && customer.getCustomerId() !=null && customer.getCustomerId().equals(customerId)){				
				customerById = customer;
				break;
			}
		}
		if(customerById ==null){
			
			throw new IllegalArgumentException("Brak użytkownika o wskazanym Id:" + customerId);
		}
		return customerById;

	}

}
