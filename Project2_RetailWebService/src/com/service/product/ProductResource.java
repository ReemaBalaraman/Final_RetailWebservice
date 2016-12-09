package com.service.product;

import java.util.Date;
import java.util.Map;
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
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.UriInfo;

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;

import model.order.ProductOrder;

import com.service.representation.order.OrderRequest;
import com.service.representation.product.ProductRepresentation;
import com.service.representation.product.ProductRequest;
import com.service.workflow.product.ProductActivity;

@CrossOriginResourceSharing(
        allowOrigins = { "http://localhost:8080" }, 
        exposeHeaders = { "X-custom-3", "X-custom-4" }
   )
@Path("/productservice/")
public class ProductResource implements ProductService {

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/product")
	//@Cacheable(cc="public, maxAge=3600") example for caching
	public Set<ProductRepresentation> getProduct() {
		System.out.println("GET METHOD Request for all customers .............");
		ProductActivity proActivity = new ProductActivity();
		return proActivity.getProduct();	
	}
	
	@GET
	@CrossOriginResourceSharing(
	         allowOrigins = { "http://localhost:8080" },  
	         exposeHeaders = { "X-custom-3", "X-custom-4" }
	    )
	@Produces({"application/json"})
	@Path("/product/{productId}")
	public ProductRepresentation getProduct(@PathParam("productId") String id,@Context UriInfo uriInfo) {
		System.out.println("GET METHOD Request from Client with productId String ............." + id);
		ProductActivity proActivity = new ProductActivity();
		ProductRepresentation prdRep = proActivity.getProduct(id);
		System.out.println("Product fetch is" + prdRep.getProductID() );
		/*Adding links*/
		prdRep.addLink(getUriForSelf(uriInfo,prdRep), "self", "Get", "application/json");
		prdRep.addLink(getUriForBuy(uriInfo,prdRep),"buy", "POST", "application/json");
		prdRep.addLink(getUriForDelete(uriInfo,prdRep), "delete", "Delete", "application/json");
		return prdRep;
		/*return Response.status(200)
        .header("Access-Control-Allow-Origin", "*")
        .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
        .header("Access-Control-Allow-Credentials", "true")
        .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
        .header("Access-Control-Max-Age", "1209600")
        .entity(prdRep)
        .build();*/
	}
	
	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/product")
	public String addProduct(ProductRequest  productRequest) {
		System.out.println("POST METHOD Request from Client with .............Produst detauls" );
		ProductActivity proActivity = new ProductActivity();
		return proActivity.addProduct(productRequest.getProductID(),productRequest.getProductDescription(),productRequest.getUnitPrice(),productRequest.getAvailableQuantity());
	}
	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/product/{productId}")
	public Response deleteProduct(@PathParam("productId") String id) {
		System.out.println("Delete METHOD Request from Client with productId String ............." + id);
		ProductActivity proActivity = new ProductActivity();
		String res = proActivity.deleteProduct(id);
		if (res.equals("OK")) {
			return Response.status(Status.OK).build();
		}
		return null;
	}
	
	@POST
	@Produces({"application/json"})
	@Path("/product/buy")
	public String buyProduct(OrderRequest  orderRequest)
	{
		ProductActivity proActivity = new ProductActivity();
		return proActivity.buyProduct(orderRequest.getCustomerEmail(), orderRequest.getOrderDate(), orderRequest.getProductOrder());
	}
	
	/*Method to generate link for itself*/
	private String getUriForSelf(UriInfo uriInfo,ProductRepresentation prdRep){
		String url = uriInfo.getBaseUriBuilder()
							.path(ProductResource.class)
							.path("product")
							.path(Integer.toString(prdRep.getProductID()))
							.build()
							.toString();
		return url;
		
	}
	/*Method to generate link to Buy product*/
	private String getUriForBuy(UriInfo uriInfo,ProductRepresentation prdRep){
		String url = uriInfo.getBaseUriBuilder()
							.path(ProductResource.class)
							.path("product")
							.path("buy")
							.build()
							.toString();
		return url;
		
	}
	/*Method to generate link to Delete product*/
	private String getUriForDelete(UriInfo uriInfo,ProductRepresentation prdRep){
		String url = uriInfo.getBaseUriBuilder()
							.path(ProductResource.class)
							.path("product")
							.path(Integer.toString(prdRep.getProductID()))
							.build()
							.toString();
		return url;
		
	}
	
}