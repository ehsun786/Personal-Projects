package eMarket.controller;

import java.util.List;

import eMarket.domain.Product;

public class ItemFormDto {
	private int orderId=-1;
	private int id=0;
	private List<Product> productList;
	private int productId = -1;
	private int amount = 0;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getId() {
		return id;
	}
	public void setId(int itemId) {
		this.id = itemId;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public List<Product> getProductList() {
		return this.productList;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
    
}
