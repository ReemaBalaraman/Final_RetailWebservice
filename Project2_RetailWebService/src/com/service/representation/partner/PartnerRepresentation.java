package com.service.representation.partner;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.domain.model.Link;

import model.product.Product;


@XmlRootElement(name = "Partner")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class PartnerRepresentation {
	
	private int partnerID;
	private String partnerType;
	private String partnerName;
	private List<Link> links = new ArrayList<>();
	public int getPartnerID() {
		return partnerID;
	}
	public void setPartnerID(int partnerID) {
		this.partnerID = partnerID;
	}
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getpartnerType() {
		return partnerType;
	}
	public void setPartnerType(String partnerType) {
		this.partnerType = partnerType;
	}
	
	public void setProduct(Product product) {
		// TODO Auto-generated method stub
		
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
