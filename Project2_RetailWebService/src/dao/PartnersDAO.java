package dao;



import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.customer.Customer;
import model.customer.CustomerAddress;
import model.customer.CustomerPhone;
import model.order.Order;
import model.order.ProductOrder;
import model.partner.Partners;
import model.product.Product;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class PartnersDAO {
	Configuration configuration = new Configuration().configure();
	public void dbConnection()
	{
		configuration.configure("hibernate.cfg.xml");
		configuration.addAnnotatedClass(Customer.class);
		configuration.addAnnotatedClass(CustomerAddress.class);
		configuration.addAnnotatedClass(CustomerPhone.class);
		configuration.addAnnotatedClass(Order.class);
		configuration.addAnnotatedClass(ProductOrder.class);
		configuration.addAnnotatedClass(Product.class);
		configuration.addAnnotatedClass(Partners.class);
		
	}
	
	
	public void addPartner(Partners partner) {
		 try
		    {
			 dbConnection();
			 
	    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
	    SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	    System.out.println("Hibernate Configuration loaded");
	    
	    
		// opens a new session from the session factory
   Session session = sessionFactory.openSession();
   org.hibernate.Transaction t =  session.beginTransaction();
	 
	        session.save(partner);                              
	        session.save(partner);
	 
	      session.flush(); // stmt.executeBatch()
	      t.commit(); // con.commit();
	      System.out.println("Records inserted");
	    

	}catch (Throwable ex) {
// Make sure you log the exception, as it might be swallowed
System.err.println("Initial SessionFactory creation failed." + ex);
throw new ExceptionInInitializerError(ex);
}
	}


	public Set<Partners> fetchAllPartners(){
		try
		{
			dbConnection();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			System.out.println("Hibernate Configuration loaded");	
			// opens a new session from the session factory
			Session session = sessionFactory.openSession();
			org.hibernate.Transaction t =  session.beginTransaction();
			String hql = "FROM Partners p";
				Query query = session.createQuery(hql);

			List results = query.list();
			Set<Partners> partnersList = new HashSet<Partners>();
			for(Object result : results)
			{
				Partners partners = new Partners(); 
				partners = (Partners)result;
				partnersList.add(partners);
			}
			

			session.flush(); // stmt.executeBatch()
			t.commit(); // con.commit();
			System.out.println("Records fetched");
			return  partnersList;  

		}catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}	

	public Partners fetchPartners(String id){
		try
		{
			dbConnection();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			System.out.println("Hibernate Configuration loaded");	
			// opens a new session from the session factory
			Session session = sessionFactory.openSession();
			org.hibernate.Transaction t =  session.beginTransaction();
			String hql = "FROM Partners p WHERE p.partnerID = :identifier";
				Query query = session.createQuery(hql);
				query.setParameter("identifier",Integer.parseInt(id));
			

			List results = query.list();
			Partners partners = (Partners)results.get(0);

			session.flush(); // stmt.executeBatch()
			t.commit(); // con.commit();
			System.out.println("Records fetched");
			return  partners;  

		}catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}	
}
