package com.service.workflow.product;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.service.representation.product.ProductRepresentation;


import model.product.Product;

import model.product.ProductManager;
import model.order.ProductOrder;


/**
 * This class' responsibility is to manage the workflow of accessing/creating/updating/deleting resources
 * using the CustomerDOA object.  
 *
 */
public class ProductActivity {

	private  ProductManager pm = new ProductManager();

	public Set<ProductRepresentation> getProduct() {

		Set<Product> products = new HashSet<Product>();
		Set<ProductRepresentation> prodRepresentations = new HashSet<ProductRepresentation>();

		products = pm.getAllProducts();

		Iterator<Product> it = products.iterator();
		while(it.hasNext()) {
			Product prod = (Product)it.next();
			ProductRepresentation prodRepresentation = new ProductRepresentation();
			prodRepresentation.setProductID(prod.getProductID());
			prodRepresentation.setProductDescription(prod.getProductDescription());
			prodRepresentation.setUnitPrice(prod.getUnitPrice());
			prodRepresentation.setAvailableQuantity(prod.getAvailableQuantity());
			//prodRepresentation.setProductOrder(prod.getProductOrder());
			//prodRepresentation.setProductOrder(prod.getProductOrder());

			//now add this representation in the list
			prodRepresentations.add(prodRepresentation);
		}
		return prodRepresentations;
	}

	public ProductRepresentation getProduct(String id) {

		Product prod = new Product();
		prod = pm.serachProduct(id);

		ProductRepresentation prdRep = new ProductRepresentation();

		prdRep.setProductID(prod.getProductID());
		prdRep.setProductDescription(prod.getProductDescription());
		prdRep.setUnitPrice(prod.getUnitPrice());
		prdRep.setAvailableQuantity(prod.getAvailableQuantity());


		return prdRep;
	}

	public String addProduct(int productID,String productDescription, double unitPrice, int availableQuantity) {

		//Employee emp = dao.addEmployee(firstName, lastName);
		String status = pm.addProduct(productID,productDescription, unitPrice, availableQuantity);

		return status;
	}

	public String deleteProduct(String id) {

		String status = pm.deleteProduct(id);

		return status;
	}

	public String buyProduct(String email,Date orderDate,Set<ProductOrder> productQuantity){

		return pm.buyProduct(email, orderDate, productQuantity);



	}

}
