package il.ac.telhai.finalProject;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

import java.util.List;

public class WebStoreDAO implements IWebStoreDAO<Product>{

	private static WebStoreDAO productHybernateDAO = null;
	private static SessionFactory factory = null;

	private WebStoreDAO()
	{
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
	}
	
	public static WebStoreDAO getProductHybernateDAO(){
		if (productHybernateDAO != null)
			return productHybernateDAO;
		productHybernateDAO = new WebStoreDAO();
		return productHybernateDAO;
	}
	
	/**
	 * Get product with the id (key in database) from the database
	 * @throws HibernateException in case cannot open the session
	 * @throws WebStoreDAOException in case cannot add the product to the database
	 */
	@Override
	public Product getProduct(Integer id) throws WebStoreDAOException{
		
		Session session = null;
		try		
		{
			session = factory.openSession();
			session.beginTransaction();
			Product product = (Product)session.get(new Product().getClass(), id);
//			another option to the Class Object
//			Product product = (Product)session.get(Class.forName("il.ac.telhai.finalProject.Product"), i);
			return product;
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new WebStoreDAOException();
		} finally	{
			if (session != null)
				session.close();			
		}
	}
	
	/**
	 * Get all the products in the database
	 * @throws HibernateException in case cannot open the session
	 * @throws WebStoreDAOException in case cannot add the product to the database
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProducts() throws WebStoreDAOException{
		Session session = null;
		try {
			session = factory.openSession();
			session.beginTransaction();
//			List<Product> products = session.createQuery("from Product").list();
//			return products.toArray(new Product[products.size()]);
			return session.createQuery("from Product").list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new WebStoreDAOException();
		} finally	{
			if (session != null)
				session.close();			
		}
	}

	/**
	 * Add a new product to the webStore database
	 * @param Product product: the new product to add
	 * @throws HibernateException in case cannot open the session
	 * @throws WebStoreDAOException in case cannot add the product to the database
	 */
	@Override
	public void addProduct(Product product) throws WebStoreDAOException{
		Session session = null;
		
		try {
			session = factory.openSession();
			session.beginTransaction();
			session.save(product);		
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new WebStoreDAOException();
		} finally	{
			if (session != null)
				session.close();			
		}
	}
	
	/**
	 * Delete the given Product obj
	 * @param Product product: the new product to add
	 * @throws HibernateException in case cannot open the session
	 * @throws WebStoreDAOException in case cannot add the product to the database
	 */
	@Override
	public void deleteProduct(Product obj) throws WebStoreDAOException {
		Session session = null;	
		
		try 	
		{
			session = factory.openSession();
			session.beginTransaction();
			session.delete(obj);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebStoreDAOException();
		} finally	{
			if (session != null)
				session.close();			
		}
	}
	
	/**
	 * Delete the given product with the id
	 * @param Product product: the new product to add
	 * @throws HibernateException in case cannot open the session
	 * @throws WebStoreDAOException in case cannot add the product to the database
	 */
	@Override
	public void deleteProduct(Integer id) throws WebStoreDAOException {
		Product p = getProduct(id);
		deleteProduct(p);
	}

	@Override
	public boolean getExistingUser(String username) throws WebStoreDAOException {
		Session session = null;
		try 	
		{
			session = factory.openSession();
			session.beginTransaction();
			User user = (User)session.get(Class.forName("il.ac.telhai.finalProject.User"), username);
			return user!=null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebStoreDAOException();
		} finally	{
			if (session != null)
				session.close();			
		}
	}

	@Override
	public void addUser(User user) throws WebStoreDAOException {
		Session session = null;	
				
		try 	
		{
			session = factory.openSession();
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebStoreDAOException();
		} finally	{
			if (session != null)
				session.close();			
		}
	}

	@Override
	public void removeUser(User user) throws WebStoreDAOException {
		Session session = null;	
		
		try 	
		{
			session = factory.openSession();
			session.beginTransaction();
			session.delete(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebStoreDAOException();
		} finally	{
			if (session != null)
				session.close();			
		}
		
	}

	@Override
	public boolean validateUserPassword(User user) throws WebStoreDAOException {
		Session session = null;
		try 	
		{
			session = factory.openSession();
			session.beginTransaction();
			User userInDB = (User)session.get(Class.forName("il.ac.telhai.finalProject.User"), user.getName());
			return userInDB.getPassword().equals(user.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebStoreDAOException();
		} finally	{
			if (session != null)
				session.close();			
		}
	}
	
}
