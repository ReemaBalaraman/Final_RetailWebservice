package com.service.order;

import java.util.Set;

import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.CacheControl;

import com.service.customer.CustomerResource;
import com.service.representation.customer.CustomerRepresentation;
import com.service.representation.order.OrderRepresentation;
import com.service.representation.order.OrderRequest;
import com.service.workflow.order.OrderActivity;

@WebService(targetNamespace = "http://order.service.com/", endpointInterface = "com.service.order.OrderService", portName = "OrderResourcePort", serviceName = "OrderResourceService")
@Path("/orderservice/")
public class OrderResource implements OrderService {

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/order")
	//@Cacheable(cc="public, maxAge=3600") example for caching
	public Set<OrderRepresentation> getOrder() {
		System.out.println("GET METHOD Request for all orders .............");
		OrderActivity ordActivity = new OrderActivity();
		return ordActivity.getOrder();	
	}
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/order/{orderId}")
	public OrderRepresentation getOrder(@PathParam("orderId") String id,@Context UriInfo uriInfo) {
		System.out.println("GET METHOD Request from Client with orderID String ............." + id);
		OrderActivity ordActivity = new OrderActivity();
		OrderRepresentation ordRep = ordActivity.getOrder(id);
		/*Adding links*/
		ordRep.addLink(getUriForSelf(uriInfo,ordRep), "self", "Get", "application/json");
		ordRep.addLink(getUriForAdd(uriInfo,ordRep),"add", "POST", "application/json");
		ordRep.addLink(getUriForCancel(uriInfo,ordRep), "cancel", "GET", "application/json");
		ordRep.addLink(getUriForOrderStatus(uriInfo,ordRep),"orderstatus", "Get", "application/json");
		ordRep.addLink(getUriForCustomer(uriInfo,ordRep),"customer", "Get", "application/json");
		return ordRep;
	}
	
	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/order")
	public String addOrder(OrderRequest  orderRequest) {
		//System.out.println("POST METHOD Request from Client with ............." + employeeRequest.getFirstName() + "  " + employeeRequest.getLastName());
		OrderActivity ordActivity = new OrderActivity();
		return ordActivity.addOrder(orderRequest.getOrderDate(),orderRequest.getTotalPrice(), orderRequest.getProductOrder(),orderRequest.getCustomerEmail());
	}


	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/order/cancel/{orderId}")
	public String cancelOrder(@PathParam("orderId") String id) {
		OrderActivity ordActivity = new OrderActivity();
		return ordActivity.cancelOrder(id);
	
	}

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/order/status/{orderId}")
	public String fetchOrderStatus(@PathParam("orderId") String id) {
		OrderActivity ordActivity = new OrderActivity();
		return ordActivity.fetchOrderStatus(id);
	
	}
	/*Method to generate link for itself*/
	private String getUriForSelf(UriInfo uriInfo,OrderRepresentation ordRep){
		String url = uriInfo.getBaseUriBuilder()
							.path(OrderResource.class)
							.path("order")
							.path("Order_ID" +ordRep.getOrderID())
							.build()
							.toString();
		return url;
		
	}
	/*Method to generate link to add order*/
	private String getUriForAdd(UriInfo uriInfo, OrderRepresentation ordRep) {
		String url = uriInfo.getBaseUriBuilder()
				.path(OrderResource.class)
				.path("order")
				.build()
				.toString();
		return url;
	}
	/*Method to generate link to cancel order*/
	private String getUriForCancel(UriInfo uriInfo, OrderRepresentation ordRep) {
		String url = uriInfo.getBaseUriBuilder()
				.path(OrderResource.class)
				.path("order")
				.path("cancel")
				.path(Integer.toString(ordRep.getOrderID()))
				.build()
				.toString();
		return url;
	}
	/*Method to generate link to order status*/
	private String getUriForOrderStatus(UriInfo uriInfo, OrderRepresentation ordRep) {
		String url = uriInfo.getBaseUriBuilder()
				.path(OrderResource.class)
				.path("order")
				.path("status")
				.path(Integer.toString(ordRep.getOrderID()))
				.build()
				.toString();
		return url;
	}
	/*Method to generate link to customer*/
	private String getUriForCustomer(UriInfo uriInfo, OrderRepresentation ordRep) {
		String url = uriInfo.getBaseUriBuilder()
				.path(CustomerResource.class)
				.path("customer")
				.path(Integer.toString(ordRep.getOrderID()))
				.build()
				.toString();
		return url;
	}
	
}