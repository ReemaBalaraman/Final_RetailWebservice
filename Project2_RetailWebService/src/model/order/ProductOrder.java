package model.order;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import model.product.Product;

@Entity
@Table(name="product_order", 
uniqueConstraints={ @UniqueConstraint(columnNames={"ProductID"}),@UniqueConstraint(columnNames={"orderID"}),})
public class ProductOrder {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="productOrderID", nullable=false, unique=true, length=11)
	private int productOrderID;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ProductID") 
	private Product product;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderID") 
	private Order order;
	private int orderQuantity;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public int getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	
	
}
