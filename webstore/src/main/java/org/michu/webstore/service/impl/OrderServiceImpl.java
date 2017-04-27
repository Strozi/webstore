package org.michu.webstore.service.impl;

import org.michu.webstore.domain.Product;
import org.michu.webstore.domain.repository.ProductRepository;
import org.michu.webstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public void processOrder(String productId, int amount) {
		
		Product productById = productRepository.getProductById(productId);
		if(productById.getUnitsInStock() < amount){
			
			throw new IllegalArgumentException("Zbyt mało towaru na stanie. Obecna liczba sztuk w magazynie:" + productById.getUnitsInStock());
		}
		productById.setUnitsInStock(productById.getUnitsInStock() - amount);
	}
	

}
