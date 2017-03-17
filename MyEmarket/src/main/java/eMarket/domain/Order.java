package eMarket.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="StoreOrder") // order is a reserved word in MySQL
public class Order {

	private String user = "";
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
	@Column
    private LocalDate date = LocalDate.now();
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
    private List<OrderItem> itemList = new ArrayList<>();
	@Column
    private Double cost = 0.0;

	public Order() { }
	
	public String getDescription() {
		// generate a comma-separated list with the names of the products purchased
		List<String> list = itemList.stream().map(i -> i.getProduct().getName() ).collect(Collectors.toList());
		return String.join(", ", list);
	}

	public void addItem(Product product, int amount) {
		this.getItemList().add(new OrderItem(product,amount));
		updateCost();
	}
	
	public void updateCost() {
		cost = 0.0;
		this.getItemList().forEach(i -> cost += i.getAmount() * i.getProduct().getPrice());
	}


	public List<OrderItem> getItemList() {
		return itemList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setItemList(List<OrderItem> itemList) {
		this.itemList = itemList;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
		
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getCost() {
		return cost;
	}


}
