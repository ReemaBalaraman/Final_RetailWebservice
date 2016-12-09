package model.partner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Proxy;

import model.product.Product;

@Entity
@Table(name="partners", 
uniqueConstraints={ @UniqueConstraint(columnNames={"partnerID"})})
@Proxy(lazy=false)
public class Partners {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int partnerID;
	@Column(name="partnerType", length=25, nullable=true)
	private String partnerType;
	@Column(name="partnerName", length=25, nullable=true)
	private String partnerName;
	//private Product product;
	
	

	public void setPartnerID(int partnerID) {
		this.partnerID = partnerID;
	}

	public void setPartnerType(String partnerType) {
		this.partnerType = partnerType;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}



	public int getPartnerID() {
		return partnerID;
	}

	public String getPartnerType() {
		return partnerType;
	}

	public String getPartnerName() {
		return partnerName;
	}


	
	

}