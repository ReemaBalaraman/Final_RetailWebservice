package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.service.representation.customer.CustomerRepresentation;
import com.service.representation.order.OrderRepresentation;
import com.service.workflow.customer.CustomerActivity;
import com.service.workflow.order.OrderActivity;

import dao.ProductDAO;

import model.customer.CustomerAddress;
import model.customer.CustomerPhone;
import model.paymentdetails.PaymentDetails;
import model.paymentdetails.PaymentDetailsManger;
import model.product.Product;





public class Main {
	public static void main(String []args){
		Main main = new Main();
		//main.productSearch();
		//main.paymentDetailsCheck();
		//main.addCustomer();
       //main.fetchCustomer();
		//main.authenticateCustomer();
		//main.cancelOrder();
		//main.fetchOrder();
		main.fetchOrderStatus();
		
		
	}
		
	

	// 1. Product Search Function call
	private void productSearch(){
		System.out.println("Product search start :" + " ");
	ProductDAO prodDAO = new ProductDAO();
	Product product = prodDAO.searchProduct("1");
	System.out.println("Product search result :" + " " 
	+"Product Description - "+product.getProductDescription()+" "+"unit Price - "+  product.getUnitPrice());
	}
	
	// 2. Payment Detail Validation
	private void paymentDetailsCheck()
	{
	SimpleDateFormat dateformat = new SimpleDateFormat("MM/yyyy");
	Date date1 = null;
	try
	{
	 date1 = dateformat.parse("07/1989");
	}
	catch (ParseException e) {
		
        e.printStackTrace();
    }

	PaymentDetails pd = new PaymentDetails(1234567812345678L,889,"Reema",date1);
	PaymentDetailsManger pdm = new PaymentDetailsManger();
	pdm.paymentDetailsCheck(pd, 20.05);
	
	}
	
	//add Customer
public void addCustomer(){
	CustomerActivity ca = new CustomerActivity();
	String name = "TestCust";
	String email = "test1@gmail.com";
	String password = "test123";
	CustomerAddress address = new CustomerAddress("testArea","testCity","testState","60660");
	CustomerPhone phone = new CustomerPhone("1234567","7654321");
	System.out.println(ca.addCustomer(name, email, password,phone, address));
}

public void authenticateCustomer(){
	CustomerActivity ca = new CustomerActivity();
	String email = "test31@gmail.com";
	String password = "test123";
	System.out.println(ca.authenticateCustomer(email, password));
}
public void fetchCustomer()
{
	CustomerActivity ca = new CustomerActivity();
	//CustomerRepresentation cr = ca.getCustomer("test1@gmail.com");
	Set<CustomerRepresentation> setCR = ca.getCustomer();
	for(CustomerRepresentation cr : setCR)
	{
	System.out.println(cr.getCustomerID() + " "+ cr.getEmail())	;
	}
}
public void fetchOrder()
{
	OrderActivity ca = new OrderActivity();
	Set<OrderRepresentation> setCR = ca.getOrder();
	for(OrderRepresentation cr : setCR)
	{
	System.out.println(cr.getOrderStatus())	;
	}
}
public void cancelOrder(){
	OrderActivity ca = new OrderActivity();
	System.out.println(ca.cancelOrder("1"));
}
public void fetchOrderStatus(){
	OrderActivity ca = new OrderActivity();
	System.out.println(ca.fetchOrderStatus("2"));
}
}
