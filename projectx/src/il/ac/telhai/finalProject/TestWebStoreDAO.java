package il.ac.telhai.finalProject;

//import java.io.IOException;
//
//import org.apache.log4j.BasicConfigurator;
//import org.apache.log4j.FileAppender;
//import org.apache.log4j.Logger;
//import org.apache.log4j.SimpleLayout;
 

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.hibernate.HibernateException;
import java.util.Random;

import java.util.List;

public class TestWebStoreDAO {
	
	private WebStoreDAO db = WebStoreDAO.getProductHybernateDAO();
	private List<Product> initProducts;
	private List<Product> products; 
	private Random random= new Random(); 
	private final String[] productsName = {"Hat","T-Shirt","Pants","Galaxy-s2","Book","Phone","Camera","TV-3D","Computer","Screen"};
	
	@Before
	public void initTestWebStoreDAO() throws Exception {
		initProducts = products = db.getProducts();
	}
	
	@Test
	public void testAddProduct() throws Exception {
		try	{
			int i = random.nextInt(productsName.length);
			Product product = new Product(productsName[i], random.nextDouble()*10000);
			db.addProduct(product);
		} 	catch (WebStoreDAOException e)	{
			assertTrue(false);
		}	catch (HibernateException e)	{
			assertTrue(false);
		}	catch (Exception e)	{
			assertTrue(false);
		}
	}
	
	@Test
	public void StormTestAddProduct() throws Exception {
		for (int i=0; i < 10 ; i++){
			testAddProduct();
		}
		testGetProducts();
	}
	
	@Test
	public void testGetProduct() throws Exception {
		try	{
			products = db.getProducts();
			for(Product p : products)
				db.getProduct(p.getId());
		} 	catch (WebStoreDAOException e)	{
			assertTrue(false);
		}	catch (HibernateException e)	{
			assertTrue(false);
		}	catch (Exception e)	{
			assertTrue(false);
		}
	}
	
	@Test
	public void testGetProducts() throws Exception {
		try	{
			products = db.getProducts();
			for(Product p : products)
				System.out.println(p);
		} 	catch (WebStoreDAOException e)	{
			assertTrue(false);
		}	catch (HibernateException e)	{
			assertTrue(false);
		}	catch (Exception e)	{
			assertTrue(false);
		}
	}
	
	@After
	public void testDeleteAllProduct() throws Exception {
		try	{
			products = db.getProducts();
			for(Product p : products)	{
				boolean isInitProduct = false;
				for(Product init_p : initProducts)	{
					if (init_p.getId() == p.getId())	{
						isInitProduct = true;
						break;
					}
				}
				if (! isInitProduct){
					System.out.println("-I- Deleting Product: " + p.toString());
					db.deleteProduct(p);
					System.out.println("-I- Delete was seccesfull");
				}	else	{
					System.out.println("-I Produte :" + p + " was not deleted, because it was in the DB before the execute J-Unit");
				}
			}
		} 	catch (WebStoreDAOException e)	{
			assertTrue(false);
		}	catch (HibernateException e)	{
			assertTrue(false);
		}	catch (Exception e)	{
			assertTrue(false);
		}
	}

	private User testedUser = new User("zzzxxxxccc", "B^$GV&>");
	@Test
	public void testAddUser() throws Exception {
		testAddUser(testedUser);
	}
	
	public void testAddUser(User user) throws Exception {
		try	{
			db.addUser(user);
			assertTrue("could not find the given username", db.getExistingUser(user.getName()));
		} 	catch (WebStoreDAOException e)	{
			assertTrue(false);
		}	catch (HibernateException e)	{
			assertTrue(false);
		}	catch (Exception e)	{
			assertTrue(false);
		}
	}
	@Test
	public void testGetExistingUserForExistence() throws Exception {
		testGetExistingUserForExistence(testedUser.getName());
	}
	
	public void testGetExistingUserForExistence(String name) throws Exception {
		try	{
			assertTrue("could not find the given username", db.getExistingUser(name));
		} 	catch (WebStoreDAOException e)	{
			assertTrue(false);
		}	catch (HibernateException e)	{
			assertTrue(false);
		}	catch (Exception e)	{
			assertTrue(false);
		}
	}
	
	@Test
	public void testRemoveUser()	{
		testRemoveUser(testedUser);
	}
	
	public void testRemoveUser(User user)	{
		try	{
			db.removeUser(user);
		} 	catch (WebStoreDAOException e)	{
			assertTrue(false);
		}	catch (HibernateException e)	{
			assertTrue(false);
		}	catch (Exception e)	{
			assertTrue(false);
		}
	}
}
