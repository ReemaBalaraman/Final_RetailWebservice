package com.service.workflow.order;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.service.representation.order.OrderRepresentation;


import model.customer.Customer;
import model.order.Order;
import model.order.OrderManager;
import model.order.ProductOrder;


/**
 * This class' responsibility is to manage the workflow of accessing/creating/updating/deleting resources
 * using the CustomerDOA object.  
 *
 */
public class OrderActivity {

	//private static EmployeeDAO dao = new EmployeeDAO();
	private static OrderManager om = new OrderManager();
	
	public Set<OrderRepresentation> getOrder() {
		
		Set<Order> orders = new HashSet<Order>();
		Set<OrderRepresentation> ordRepresentations = new HashSet<OrderRepresentation>();

		orders = om.getAllOrders();
		
		Iterator<Order> it = orders.iterator();
		while(it.hasNext()) {
			Order ord = (Order)it.next();
          OrderRepresentation orderRepresentation = new OrderRepresentation();
          orderRepresentation.setOrderID(ord.getOrderID());
          orderRepresentation.setOrderStatus(ord.getOrderStatus());
          orderRepresentation.setOrderDate(ord.getOrderDate());
          orderRepresentation.setTotalPrice(ord.getTotalPrice());
          orderRepresentation.setProductOrder(ord.getProductOrder());
          orderRepresentation.setCustomer(ord.getCustomer());
          
          //now add this representation in the list
          ordRepresentations.add(orderRepresentation);
        }
		return ordRepresentations;
	}
	
	public OrderRepresentation getOrder(String id) {
		
		Order ord = new Order();
		ord = om.getOrder(id);
		
		OrderRepresentation ordRep = new OrderRepresentation();

		ordRep.setOrderID(ord.getOrderID());
		ordRep.setOrderDate(ord.getOrderDate());
		ordRep.setOrderStatus(ord.getOrderStatus());
		ordRep.setTotalPrice(ord.getTotalPrice());
		ordRep.setProductOrder(ord.getProductOrder());
		ordRep.setCustomer(ord.getCustomer());
		
		return ordRep;
	}
	
	public String addOrder(Date orderDate, double totalPrice, Set<ProductOrder> productOrder,String customerEmail) {

		String status = om.orderPlacement(orderDate, totalPrice, productOrder, customerEmail);
		
		return status;
	}
	
	public String cancelOrder(String id) {
		
		String status = om.cancelOrder(id);
		
		return status;
	}
public String fetchOrderStatus(String id) {
		
		String status = om.fetchOrderStatus(id);
		
		return status;
	}
}
