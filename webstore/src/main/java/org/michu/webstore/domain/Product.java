package org.michu.webstore.domain;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;
@XmlRootElement
public class Product {
	
	private String productID;
	private String name;
	private BigDecimal unitPrice; 
	private String description;
	private String manufacturer;
	private String category;
	private long unitsInStock;
	private long unitsInOrder;
	private boolean discontinued; 
	private String condition;
	@JsonIgnore
	private MultipartFile productImage;
	
	public Product(){
		
		super();
	}

	public Product(String productID, String name, BigDecimal unitPrice) {
		super();
		this.productID = productID;
		this.name = name;
		this.unitPrice = unitPrice;
	}
	
	
	
	
	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(long unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public long getUnitsInOrder() {
		return unitsInOrder;
	}

	public void setUnitsInOrder(long unitsInOrder) {
		this.unitsInOrder = unitsInOrder;
	}

	public boolean isDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(boolean discontinued) {
		this.discontinued = discontinued;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	@XmlTransient
	public MultipartFile getProductImage() {
		return productImage;
	}

	public void setProductImage(MultipartFile productImage) {
		this.productImage = productImage;
	}

	@Override
	public boolean equals(Object object){
		//the same reference
		if(this == object){
			return true;
		}
		//passed null object
		if(object == null){
			return false;
		}
		// are both compared object of the same type?
		if(getClass() != object.getClass()){
			return false;
		}
		
		Product other = (Product) object;
		
		//acctual comparision of products based on ProductID - unique per product 
		if(productID == null){
			if(other.productID !=null){
				return false;
			}else if(!productID.equals(other.productID)){
				return false;
			}
		}
		return true;
	}
	
	@Override 
	public int hashCode(){
		
		final int prime =31;
		int result =1;
		result = prime * result + ((productID ==null) ? 0 : productID.hashCode());
		return result;
	}
	
	@Override
	public String toString(){
		
		return "Product [productId = " + productID + ", nazwa " + name + "]";
		
	}

}
