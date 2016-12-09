package model.customer;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTADDRESS1")
public class CustomerAddress {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AID")
	private long addressId;
	@Column(name = "ADDRESS_STREET", nullable = false, length=250)
	private String street;
	@Column(name = "ADDRESS_CITY", nullable = false, length=50)
	private String city;
	@Column(name = "ADDRESS_STATE", nullable = false, length=50)
	private String state;
	@Column(name = "ADDRESS_ZIPCODE", nullable = false, length=10)
	private String zipcode;

	public CustomerAddress() {
	}

	public CustomerAddress(String street, String city, String state, String zipcode) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}


	public long getAddressId() {
		return this.addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	
	
	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	
	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

}