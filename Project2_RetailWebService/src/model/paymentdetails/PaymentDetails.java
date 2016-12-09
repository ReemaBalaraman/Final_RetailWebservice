package model.paymentdetails;

import java.util.Date;

public class PaymentDetails {
	private long cardNumber;
	private int cvvCode;
	private String nameOnCard;
	private Date expirationDate;
	public PaymentDetails()
	{
		
	}
	
	public PaymentDetails(long cardNumber, int cvvCode, String nameOnCard,
			Date expirationDate) {
		super();
		this.cardNumber = cardNumber;
		this.cvvCode = cvvCode;
		this.nameOnCard = nameOnCard;
		this.expirationDate = expirationDate;
	}
	public long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getNameOnCard() {
		return nameOnCard;
	}
	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}
	public int getCvvCode() {
		return cvvCode;
	}
	public void setCvvCode(int cvvCode) {
		this.cvvCode = cvvCode;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
}
