package model.order;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import dao.CustomerDAO;
import dao.ManageOrderDAO;
import model.customer.Customer;
import model.order.Order;
import model.product.Product;

public class OrderManager {
	
	
	ManageOrderDAO moDAO = new ManageOrderDAO();
	public String orderPlacement(Date orderDate, double totalPrice, Set<ProductOrder> productOrder,String customerEmail)
	{
		Order mo = new Order();
		CustomerDAO custDAO = new CustomerDAO();
		Customer customer = custDAO.fetchCustomer(customerEmail);
		//mo.setOrderID(0);
		mo.setCustomer(customer);
		mo.setTotalPrice(totalPrice);
		mo.setOrderDate(orderDate);
		mo.setOrderStatus("Processing");
		String status = moDAO.placeOrder(mo, productOrder);
		return status;
		
	}

	public Set<Order> getAllOrders()
	{
		
		Set<Order> orders = new HashSet<Order>();
		orders = moDAO.fetchAllOrders();
		return orders;
	}
	
	public Order getOrder(String id)
	{
		
		Order order = new Order();
		order = moDAO.fetchOrder(id);
		return order;
	}
	
	public String cancelOrder(String id)
	
	{
		String status = moDAO.cancelOrder(id);
		return status;
	}
public String fetchOrderStatus(String id)
	
	{
		String status = moDAO.fetchOrderStatus(id);
		return status;
	}
}
