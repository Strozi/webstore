package org.michu.webstore.domain.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.michu.webstore.domain.Product;

public interface ProductRepository {
	
	List <Product> getAllProducts(); 
	Product getProductById(String productId);
	List<Product> getProductByCategory(String category);
	Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);	
	List<Product> getProductByManufacturer(String manufacturer);
	void addProduct(Product product);
	

}
