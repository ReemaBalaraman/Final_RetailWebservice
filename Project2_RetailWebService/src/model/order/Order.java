package model.order;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import model.customer.Customer;


@Entity
@Table(name="manageorder", 
uniqueConstraints={ @UniqueConstraint(columnNames={"orderID"})})
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="orderID", nullable=false, unique=true, length=11)
	private int orderID;
	
	@ManyToOne
	private Customer customer;
	
	private String orderStatus;
	private Date orderDate;
    private double totalPrice;
    
	@OneToMany(mappedBy = "product")
	private Set<ProductOrder> productOrder;
	

	public Order()
	{
		
	}
	
	public Order(int orderID, Customer customer, String orderStatus, Date orderDate, 
			double totalPrice) {
		super();
		this.orderID = orderID;
		this.customer = customer;
		this.orderStatus = orderStatus;
		this.orderDate = orderDate;
		this.totalPrice = totalPrice;

	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}



	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Set<ProductOrder> getProductOrder() {
		return productOrder;
	}

	public void setProductOrder(Set<ProductOrder> productOrder) {
		this.productOrder = productOrder;
	}





	
	

}
