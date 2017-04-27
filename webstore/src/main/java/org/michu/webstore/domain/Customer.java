package org.michu.webstore.domain;

public class Customer {
	
	private String customerId;
	private String name;
	private String address;
	private long noOfOrdersMade;
	
	public Customer(String customerId, String name, String address) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.address = address;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getNoOfOrdersMade() {
		return noOfOrdersMade;
	}

	public void setNoOfOrdersMade(long noOfOrdersMade) {
		this.noOfOrdersMade = noOfOrdersMade;
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
		
		Customer other = (Customer) object;
		
		//acctual comparision of products based on ProductID - unique per product 
		if(customerId == null){
			if(other.customerId !=null){
				return false;
			}else if(!customerId.equals(other.customerId)){
				return false;
			}
		}
		return true;
	}
	
	@Override 
	public int hashCode(){
		
		final int prime =31;
		int result = 1;
		result = prime * result + ((customerId ==null) ? 0 : customerId.hashCode());
		return result;
	}
	
	@Override
	public String toString(){
		
		return "Customer [customerId = " + customerId + ", mane " + name + "]";
		
	}

}
