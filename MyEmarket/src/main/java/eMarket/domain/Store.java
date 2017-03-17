package eMarket.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Store {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name="store_id")
	private int id;

	@Column
	private String name;
	
	// CascadeType.REMOVE included in CascadeType.ALL cascades removal when Store object is removed
	// orphan removal forces the deletion of the object when the reference is removed
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	List<Product> productList = new ArrayList<>();

	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	List<Order> orderList = new ArrayList<>();
	
	public Store() {}
	
	public Store(String name) {
		this.name = name;
		productList = new ArrayList<>();
		orderList = new ArrayList<>();
	}
	
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
	public List<Product> getProductList() {
		return productList;
	}

	public List<Order> getOrderList() {
		return orderList;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
