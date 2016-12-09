package com.service.representation.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.domain.model.Link;

import model.order.ProductOrder;



@XmlRootElement(name = "Product")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class ProductRepresentation {
	
	private int productID;
	private String productDescription;
	private double unitPrice;
	private int availableQuantity;
	private Set<ProductOrder> productOrder;
	private List<Link> links = new ArrayList<>();
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
	
	public void addLink(String url, String rel, String method, String mediaType){
		Link link = new Link();
		link.setUrl(url);
		link.setRel(rel);
		link.setAction(method);
		link.setMediaType(mediaType);
		links.add(link);
	}
}