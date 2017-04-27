package org.michu.webstore.service;

public interface OrderService {
	
	void processOrder(String productId, int amount);

}
