package com.service.representation.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.domain.model.Link;

import model.customer.Customer;
import model.order.ProductOrder;


@XmlRootElement(name = "Order")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderRepresentation {
	
	private int orderID;
	private String orderStatus;
	private Date orderDate;
	private double totalPrice;
	private Set<ProductOrder> productOrder;
	private List<Link> links = new ArrayList<>();
	private Customer customer;
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
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public void addLink(String url, String rel, String method, String mediaType){
		Link link = new Link();
		link.setUrl(url);
		link.setRel(rel);
		link.setAction(method);
		link.setMediaType(mediaType);
		links.add(link);
	}	
	
}
