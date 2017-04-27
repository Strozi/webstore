package org.michu.webstore.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.michu.webstore.domain.Product;
import org.michu.webstore.domain.repository.ProductRepository;
import org.michu.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// only mediate between ProductRepository and ProductController - another level of abstraction
@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> getAllProducts() {		
		return productRepository.getAllProducts();
	}

	@Override
	public List<Product> getProductByCategory(String category) {
		
		return productRepository.getProductByCategory(category);
	}

	@Override
	public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		
		return productRepository.getProductsByFilter(filterParams);
	}

	@Override
	public Product getProductById(String id) {
		return productRepository.getProductById(id);
	}

	@Override
	public List<Product> getProductByManufacturer(String manufacturer) {
		
		return productRepository.getProductByManufacturer(manufacturer);
	}

	@Override
	public void addProduct(Product product) {
		productRepository.addProduct(product);
	}



	
}
