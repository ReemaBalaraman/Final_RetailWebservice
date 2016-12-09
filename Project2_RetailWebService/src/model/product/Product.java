package model.product;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


import org.hibernate.annotations.Proxy;

import model.order.ProductOrder;

@Entity
@Table(name="product", 
uniqueConstraints={ @UniqueConstraint(columnNames={"productID"})})
@Proxy(lazy=false)
public class Product {
	@Id
	private int productID;
	@Column(name="ProductDescription", length=25, nullable=true)
	private String productDescription;
	@Column(name="UnitPrice")
	private double unitPrice;
	@Column(name="availableQuantity")
	private int availableQuantity;
	@OneToMany(mappedBy = "order")
	private Set<ProductOrder> productOrder;
	public Product()
	{

	}
	public Product(int productID, String productDescription, double unitPrice, int availableQuantity) {
		super();
		this.productID = productID;
		this.productDescription = productDescription;
		this.unitPrice = unitPrice;
		this.availableQuantity = availableQuantity;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getAvailableQuantity() {
		return availableQuantity;
	}
	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	public Set<ProductOrder> getProductOrder() {
		return productOrder;
	}
	public void setProductOrder(Set<ProductOrder> productOrder) {
		this.productOrder = productOrder;
	}
	


}
