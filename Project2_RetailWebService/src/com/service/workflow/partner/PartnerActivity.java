package com.service.workflow.partner;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.service.representation.customer.CustomerRepresentation;
import com.service.representation.partner.PartnerRepresentation;

import model.partner.Partners;
import model.product.Product;
import model.partner.PartnerManager;


/**
 * This class' responsibility is to manage the workflow of accessing/creating/updating/deleting resources
 * using the CustomerDOA object.  
 *
 */
public class PartnerActivity {

	//private static EmployeeDAO dao = new EmployeeDAO();
	private static PartnerManager pm = new PartnerManager();
	
	public Set<PartnerRepresentation> getPartner() {
		
		Set<Partners> partners = new HashSet<Partners>();
		Set<PartnerRepresentation> partRepresentations = new HashSet<PartnerRepresentation>();

		partners = pm.getAllPartners();
		
		Iterator<Partners> it = partners.iterator();
		while(it.hasNext()) {
			Partners part = (Partners)it.next();
          PartnerRepresentation partnerRepresentation = new PartnerRepresentation();
          partnerRepresentation.setPartnerID(part.getPartnerID());
          partnerRepresentation.setPartnerType(part.getPartnerType());
          partnerRepresentation.setPartnerName(part.getPartnerName());
          //partnerRepresentation.setProduct(part.getProduct());
           
          
          //now add this representation in the list
          partRepresentations.add(partnerRepresentation);
        }
		return partRepresentations;
	}
	
	public PartnerRepresentation getPartner(String partnerID) {
		
		Partners part = new Partners();
		part = pm.getPartners(partnerID);
		
		PartnerRepresentation partRep = new PartnerRepresentation();

		partRep.setPartnerID(part.getPartnerID());
		partRep.setPartnerType(part.getPartnerType());
		partRep.setPartnerName(part.getPartnerName());
		//partRep.setProduct(part.getProduct());
		
		return partRep;
	}
	
	public PartnerRepresentation addPartner(String partnerName, String partnerType, Product product) {
		

		Partners part = new Partners();
		//part = pm.getPartners(partnerID);
		
		PartnerRepresentation partRep = new PartnerRepresentation();

		partRep.setPartnerID(part.getPartnerID());
		partRep.setPartnerType(part.getPartnerType());
		partRep.setPartnerName(part.getPartnerName());
		//partRep.setProduct(part.getProduct());
		
		return partRep;
	}
	
	public String deletPartner(String id) {
		
		//dao.deleteEmployee(id);
		//em.deleteEmployee(id);
		
		return "OK";
	}
	
}
