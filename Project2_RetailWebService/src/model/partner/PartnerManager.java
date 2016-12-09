package model.partner;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import model.order.Order;
import model.product.ProductManager;

import dao.PartnersDAO;


public class PartnerManager {
	private static PartnersDAO partnerDAO = new PartnersDAO();
	public Set<Partners> getAllPartners()
	{
		
		Set<Partners> partners = new HashSet<Partners>();
		partners = partnerDAO.fetchAllPartners();
		return partners;
	}
	
	public Partners getPartners(String id)
	{
		
		Partners partner = new Partners();
		partner = partnerDAO.fetchPartners(id);
		return partner;
	}
	public Partners addPartner(){
		PartnersDAO pDAO = new PartnersDAO();
		 Partners partner = new Partners();
		    Scanner scanner = new Scanner(System.in);

		    System.out.print("Enter your name: ");
		    String partnerName = scanner.nextLine();
		    partner.setPartnerName(partnerName);
		    
		    System.out.print("Enter type of partnership: ");
		    String partnerType = scanner.nextLine();
		    partner.setPartnerType(partnerType);
		 
		    pDAO.addPartner(partner);
		return partner;
		
	}
	

}
