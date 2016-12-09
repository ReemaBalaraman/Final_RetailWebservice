package com.service.product;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.jws.WebService;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import model.order.ProductOrder;

import com.service.representation.order.OrderRequest;
import com.service.representation.product.ProductRepresentation;
import com.service.representation.product.ProductRequest;

@WebService
public interface ProductService {
	   
	public Set<ProductRepresentation> getProduct();
	public ProductRepresentation getProduct(String employeeId,@Context UriInfo uriInfo);
	public String addProduct(ProductRequest productRequest);
	public String buyProduct(OrderRequest  orderRequest);
   
    //public Response updateEmployee(EmployeeRequest employeeRequest);
    //public Response deleteEmployee(String employeeId);
	
	

}