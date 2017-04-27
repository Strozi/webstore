package org.michu.webstore.domain.repository.impl;

import java.math.BigDecimal;
import org.apache.commons.lang.math.NumberUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.michu.webstore.domain.Product;
import org.michu.webstore.domain.repository.ProductRepository;
import org.michu.webstore.exception.ProductNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryProductRepository implements ProductRepository{

	private List<Product> listOfProducts = new ArrayList<>();
	
	//as for now hardcoded simulation od data from DB - mock object
	// TODO - add real connection with DB and Hibernate 
	public InMemoryProductRepository(){
		
		Product iphone = new Product("P1234", "iPhone 5s", new BigDecimal(500));
		iphone.setDescription("Apple iPone 5s - smartfon z 4-calowym wyświetlaczem o rozdzielczości 640 x 1136 oraz 8-megapixelowym aparatem ");
		iphone.setCategory("Smartphone");
		iphone.setManufacturer("Apple");
		iphone.setUnitsInStock(1000);
		
		Product laptop_dell = new Product("P1235", "Dell Inspiron", new BigDecimal(700));
		laptop_dell.setDescription("Dell Inspiron, 14-calowy laptop (czarny) z procesorem i5 3300");
		laptop_dell.setCategory("Laptop");
		laptop_dell.setManufacturer("Dell");
		laptop_dell.setUnitsInStock(400);
		
		Product tablet_nexus = new Product("P1236", "Nexus 7", new BigDecimal(300));
		tablet_nexus.setDescription("Goodle Nexus 7 jest najlżejszym 7-calowym tabletem z 4-rdzeniowym procesorem Qualcomm Snapdragon S4 pro");
		tablet_nexus.setCategory("Tablet");
		tablet_nexus.setManufacturer("Google");
		tablet_nexus.setUnitsInStock(2000);
		
		listOfProducts.add(iphone);
		listOfProducts.add(laptop_dell);
		listOfProducts.add(tablet_nexus);
	}
	
	@Override
	public List<Product> getAllProducts() {
		
		return listOfProducts;
	}

	@Override
	public Product getProductById(String productId) {

		Product productById = null;
		for(Product product : listOfProducts){
			
			if(product!=null && product.getProductID() !=null && product.getProductID().equals(productId)){				
				productById = product;
				break;
			}
		}
		if(productById ==null){
			//our own exception
			throw new ProductNotFoundException(productId);
		}
		return productById;
	}

	@Override
	public List<Product> getProductByCategory(String category) {

		List<Product> productsByCategory = new ArrayList<>();
		for(Product product : listOfProducts){
			if(category.equalsIgnoreCase(product.getCategory())){
				productsByCategory.add(product);
			}
		}
		return productsByCategory;
	}

	@Override
	public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		
		Set<Product> productsByBrand = new HashSet<Product>();
		Set<Product> productsByCategory = new HashSet<Product>();
		Set<String> criterias = filterParams.keySet();
		if(criterias.contains("brand")){
			for(String brandName : filterParams.get("brand")){
				for(Product product : listOfProducts){
					if(brandName.equalsIgnoreCase(product.getManufacturer())){
						productsByBrand.add(product);
					}
				}
			}
		}
		if(criterias.contains("category")){
			for(String category : filterParams.get("category")){
				productsByCategory.addAll(this.getProductByCategory(category));
			}
		}
		productsByCategory.retainAll(productsByBrand);
		return productsByCategory;
	}

	@Override
	public List<Product> getProductByManufacturer(String manufacturer) {

		List<Product> productsByManufacturer = new ArrayList<>();
		for(Product product : listOfProducts){
			if(manufacturer.equalsIgnoreCase(product.getManufacturer())){
				productsByManufacturer.add(product);
			}
		}
		return productsByManufacturer;
	}

	@Override
	public void addProduct(Product product) {
		
		listOfProducts.add(product);
		
	}
	
	

	
}
