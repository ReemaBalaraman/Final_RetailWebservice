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

public class CustomerDAO {

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
	public String addCustomer(Customer customer){
		try
		{
			dbConnection();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			System.out.println("Hibernate Configuration loaded");	
			// opens a new session from the session factory
			Session session = sessionFactory.openSession();
			org.hibernate.Transaction t =  session.beginTransaction();

			session.save(customer);                              

			session.flush(); // stmt.executeBatch()
			t.commit(); // con.commit();
			System.out.println("Records inserted");
			String status = "Customer Added" ;
			return status;

		}catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public Customer authenticateCustomer(String email){
		try
		{
			dbConnection();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			System.out.println("Hibernate Configuration loaded");	
			// opens a new session from the session factory
			Session session = sessionFactory.openSession();
			org.hibernate.Transaction t =  session.beginTransaction();
            Customer customer = null;

						//Fetching based on email/customerID
						String hql = "FROM Customer c WHERE c.email = :identifier";
						Query	query = session.createQuery(hql);
							query.setParameter("identifier",email);
							List results = query.list();
							 if(results == null || results.isEmpty())
							 {
								 return customer;
							 }
				 customer = (Customer)results.get(0);
							

			session.flush(); // stmt.executeBatch()
			t.commit(); // con.commit();
			System.out.println("Records fetched");
			return customer;

		}catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public Customer fetchCustomer(String identifier){
		try
		{
			dbConnection();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			System.out.println("Hibernate Configuration loaded");	
			// opens a new session from the session factory
			Session session = sessionFactory.openSession();
			org.hibernate.Transaction t =  session.beginTransaction();
			Customer customer = null;
			String hql = "";
			Query query ;
			//Fetching based on email/customerID
			if(identifier.contains("@"))
			{
				hql = "FROM Customer c WHERE c.email = :identifier";
				query = session.createQuery(hql);
				query.setParameter("identifier",identifier);
			}
			else
			{
				hql = "FROM Customer c WHERE c.customerID = :identifier";
				query = session.createQuery(hql);
				int id = Integer.parseInt(identifier);
				query.setParameter("identifier",id);

			}
  
			List results = query.list();
			if(results == null || results.isEmpty())
			 {
				 return customer;
			 }
			customer = (Customer)results.get(0);

			session.flush(); // stmt.executeBatch()
			t.commit(); // con.commit();
			System.out.println("Records fetched");
			return  customer;  

		}catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}	
	
	public Set<Customer> fetchAllCustomers(){
		try
		{
			dbConnection();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			System.out.println("Hibernate Configuration loaded");	
			// opens a new session from the session factory
			Session session = sessionFactory.openSession();
			org.hibernate.Transaction t =  session.beginTransaction();
			Set<Customer> customers = new HashSet<Customer>();;
			String hql = "FROM Customer c";
				Query query = session.createQuery(hql);

			List results = query.list();
		
			for(Object result : results)
			{
				Customer customer = new Customer(); 
				customer = (Customer)result;
				customers.add(customer);
			}
			

			session.flush(); // stmt.executeBatch()
			t.commit(); // con.commit();
			System.out.println("Records fetched");
			return  customers;  

		}catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}	
}
