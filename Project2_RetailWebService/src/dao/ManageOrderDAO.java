package dao;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

public class ManageOrderDAO {
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
	public String placeOrder(Order mo , Set<ProductOrder> productEligible) {
		 try
		    {
			 dbConnection();
	    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
	    SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	    System.out.println("Hibernate Configuration loaded");
	    
	    
		// opens a new session from the session factory
  Session session = sessionFactory.openSession();
  org.hibernate.Transaction t =  session.beginTransaction();
  ProductOrder po = new ProductOrder();
  for(ProductOrder poEntry : productEligible)
  {
	   po.setOrder(mo);
	   po.setOrderQuantity(poEntry.getOrderQuantity());
	   po.setProduct(poEntry.getProduct());
	   session.save(po);   
  }
  String hql = "FROM Order o WHERE customer = :identifier";
	Query query = session.createQuery(hql);
	
	query.setParameter("identifier",mo.getCustomer());


	Order order = null; 

	List results = query.list();
	if(results == null || results.isEmpty())
	 {
		 return "Odrder not placed.";
	 }
order = (Order)results.get(results.size()-1);
	      session.flush(); // stmt.executeBatch()
	      t.commit(); // con.commit();
	      System.out.println("Records inserted");
String status = "Your Order has been placed succefully.Here is your order number for tracking purpose"+ " " 
	      + "OrderID:"+ " " + order.getOrderID() ;
System.out.println(status);
return status;

	}catch (Throwable ex) {
//Make sure you log the exception, as it might be swallowed
System.err.println("Initial SessionFactory creation failed." + ex);
throw new ExceptionInInitializerError(ex);
}
		
	}
	public Order fetchOrder(String id){
		try
		{
			dbConnection();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			System.out.println("Hibernate Configuration loaded");	
			// opens a new session from the session factory
			Session session = sessionFactory.openSession();
			org.hibernate.Transaction t =  session.beginTransaction();
			String hql = "FROM Order o WHERE o.orderID = :identifier";
				Query query = session.createQuery(hql);
				int idInt = Integer.parseInt(id);
				query.setParameter("identifier",idInt);

		
				Order order = null; 

				List results = query.list();
				if(results == null || results.isEmpty())
				 {
					 return order;
				 }
			 order = (Order)results.get(0);

			session.flush(); // stmt.executeBatch()
			t.commit(); // con.commit();
			System.out.println("Records fetched");
			return  order;  

		}catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}	
	
	public Set<Order> fetchAllOrders(){
		try
		{
			dbConnection();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			System.out.println("Hibernate Configuration loaded");	
			// opens a new session from the session factory
			Session session = sessionFactory.openSession();
			org.hibernate.Transaction t =  session.beginTransaction();
			String hql = "FROM Order o";
				Query query = session.createQuery(hql);

			List results = query.list();
			Set<Order> orders = new HashSet<Order>();
			for(Object result : results)
			{
				Order order = new Order(); 
				order = (Order)result;
				orders.add(order);
			}
			

			session.flush(); // stmt.executeBatch()
			t.commit(); // con.commit();
			System.out.println("Records fetched");
			return  orders;  

		}catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}	
	public String cancelOrder(String id)
	{
		try
		{
			dbConnection();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			System.out.println("Hibernate Configuration loaded");	
			// opens a new session from the session factory
			Session session = sessionFactory.openSession();
			org.hibernate.Transaction t =  session.beginTransaction();

			String hql = "update  Order o set o.orderStatus= :status WHERE o.orderID = :identifier";
				Query query = session.createQuery(hql);
				int idInt = Integer.parseInt(id);
				query.setParameter("status","Cancelled");
				query.setParameter("identifier",idInt);
				
				int result = query.executeUpdate();
				System.out.println("Rows affected: " + result);

			session.flush(); // stmt.executeBatch()
			t.commit(); // con.commit();
			System.out.println("Records updated to cancel");
			String status = "Order with OrderId:"+ " " + id + " is cancelled";
			return  status;  

		}catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public String fetchOrderStatus(String id)
	{
		try
		{
			dbConnection();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			System.out.println("Hibernate Configuration loaded");	
			// opens a new session from the session factory
			Session session = sessionFactory.openSession();
			org.hibernate.Transaction t =  session.beginTransaction();

			String hql = "FROM Order o WHERE o.orderID = :identifier";
				Query query = session.createQuery(hql);
				int idInt = Integer.parseInt(id);
				query.setParameter("identifier",idInt);
				

				Order order = null; 

				List results = query.list();
				if(results == null || results.isEmpty())
				 {
					 return "Incorrect OrderId";
				 }
				
			 order = (Order)results.get(0);


			session.flush(); // stmt.executeBatch()
			t.commit(); // con.commit();
			System.out.println("Records updated to cancel");
			String status = order.getOrderStatus();
			return  status;  

		}catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
}
