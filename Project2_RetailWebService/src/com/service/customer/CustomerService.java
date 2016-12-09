package com.service.customer;


import java.util.Set;

import javax.jws.WebService;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import com.service.representation.customer.CustomerRepresentation;
import com.service.representation.customer.CustomerRequest;

@WebService
public interface CustomerService {
	   
	public Set<CustomerRepresentation> getCustomer();
	public CustomerRepresentation getCustomer(String custId,@Context UriInfo uriInfo);
	public String addCustomer(CustomerRequest customerRequest);
	public String authenticateCustomer(CustomerRequest customerRequest);
   
    //public Response updateEmployee(EmployeeRequest employeeRequest);
    //public Response deleteEmployee(String employeeId);
	
	

}
