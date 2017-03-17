package eMarket.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	@ManyToOne
    private Product product;
	@Column
    private int amount;
	@Column
    private Double cost;
    
	public OrderItem() {}
	
    public OrderItem(Product product, int amount){
    	this.product = product;
    	this.amount = amount;
    	this.cost = amount * product.getPrice();
    }

	public Product getProduct() {
		return product;
	}

	public int getAmount() {
		return amount;
	}

	public int getId() {
		return id;
	}

	public void setProduct(Product product2) {
		this.product = product2;
		
	}

	public void setAmount(int amount2) {
		amount = amount2;
	}

	public void setCost(Double cost) {
		this.cost = cost;
		
	}
	
	public Double getCost() {
		return cost;
	}
	
    
}
