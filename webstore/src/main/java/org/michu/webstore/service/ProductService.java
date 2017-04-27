package org.michu.webstore.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.michu.webstore.domain.Product;

public interface ProductService {

	List<Product> getAllProducts();
	List<Product> getProductByCategory(String category);
	Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);
	Product getProductById(String id);
	List<Product> getProductByManufacturer(String manufacturer);
	void addProduct(Product product);
}
