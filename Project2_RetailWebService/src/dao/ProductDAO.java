package dao;

import java.sql.Statement;
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

public class ProductDAO {

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
	
	public Set<Product> fetchAllProducts(){
		try
		{
			dbConnection();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			System.out.println("Hibernate Configuration loaded");	
			// opens a new session from the session factory
			Session session = sessionFactory.openSession();
			org.hibernate.Transaction t =  session.beginTransaction();
			String hql = "FROM Product p";
				Query query = session.createQuery(hql);

			List results = query.list();
			Set<Product> products = new HashSet<Product>();
			for(Object result : results)
			{
				Product product = new Product(); 
				product = (Product)result;
				products.add(product);
			}
			

			session.flush(); // stmt.executeBatch()
			t.commit(); // con.commit();
			System.out.println("Records fetched");
			return  products;  

		}catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}	
	
	public Product searchProduct(String productID)
	{
		// loads configuration and creates a session factory
		try
		{
			dbConnection();
	    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
	    SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	    System.out.println("Hibernate Configuration loaded");
	    
	    
		// opens a new session from the session factory
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    
	    Product product = (Product) session.load(Product.class, new Integer(productID));
	    session.close();
	    return product ;
	   
		}
		catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
	}

	public String addProduct(Product product){
		try
		{
			dbConnection();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			System.out.println("Hibernate Configuration loaded");	
			// opens a new session from the session factory
			Session session = sessionFactory.openSession();
			org.hibernate.Transaction t =  session.beginTransaction();

			session.save(product);                              

			session.flush(); // stmt.executeBatch()
			t.commit(); // con.commit();
			System.out.println("Records inserted");
			String status = "Product Added Successfully" ;
			return status;

		}catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}	
	public String deleteProduct(String id ){
		try
		{
			dbConnection();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			System.out.println("Hibernate Configuration loaded");	
			// opens a new session from the session factory
			Session session = sessionFactory.openSession();
			org.hibernate.Transaction t =  session.beginTransaction();

			String hql = "delete from Product where productID= :productID";
			Query query = session.createQuery(hql);
			int idInt = Integer.parseInt(id);
			query.setParameter("productID",idInt);
			
			int result = query.executeUpdate();
			System.out.println("Rows affected: " + result);

		session.flush(); // stmt.executeBatch()
		t.commit(); // con.commit();
		System.out.println("Records updated to cancel");
		String status = "OK";
		return  status;

		}catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}	
}
