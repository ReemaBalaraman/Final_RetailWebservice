package com.service.representation.customer;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.domain.model.Link;

import model.customer.CustomerAddress;
import model.customer.CustomerPhone;

@XmlRootElement(name = "Customer")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class CustomerRepresentation {
	
	private int customerID;
	private String customerName;
	private CustomerAddress customerAddress;
	private CustomerPhone customerPhone;
	private String email;
	private String password;
	private List<Link> links = new ArrayList<>();
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public CustomerAddress getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(CustomerAddress customerAddress) {
		this.customerAddress = customerAddress;
	}
	public CustomerPhone getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(CustomerPhone customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
