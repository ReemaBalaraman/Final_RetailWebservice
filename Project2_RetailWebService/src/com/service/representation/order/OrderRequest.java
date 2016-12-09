package com.service.representation.order;

import java.util.Date;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import model.customer.Customer;
import model.order.ProductOrder;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderRequest {
	
	private int orderID;
	private String orderStatus;
	private Date orderDate;
	private double totalPrice;
	private Set<ProductOrder> productOrder;
	private String customerEmail;
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
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
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customer) {
		this.customerEmail = customer;
	}
	
	
	
}
