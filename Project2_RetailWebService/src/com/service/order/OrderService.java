package com.service.order;

import java.util.Set;

import javax.jws.WebService;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import com.service.representation.order.OrderRepresentation;
import com.service.representation.order.OrderRequest;
import com.service.representation.customer.CustomerRequest;

@WebService
public interface OrderService {
	   
	public Set<OrderRepresentation> getOrder();
	public OrderRepresentation getOrder(String orderId,@Context UriInfo uriInfo);
	public String addOrder(OrderRequest orderRequest);
	public String cancelOrder(String orderId);
	public String fetchOrderStatus(String orderId);
   
    //public Response updateEmployee(EmployeeRequest employeeRequest);
    //public Response deleteEmployee(String employeeId);
	
	

}
