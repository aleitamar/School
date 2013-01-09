package il.ac.telhai.finalProject;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Random;

import java.util.List;


public class TestShoppingCart {
	
	private ShoppingCart sc;
	private WebStoreDAO db;
	private List<Product> products;
	private Random random= new Random(); 
	
	
	// ########### Before tests #######################
	@Before
	/**
	 * Initialize object for the tests
	 * @throws WebStoreDAOException 
	 */
	public void initTestShoppingCartTests() throws WebStoreDAOException {
		try {
			sc = new ShoppingCart();
			db = WebStoreDAO.getProductHybernateDAO();
			products = db.getProducts();
		} catch (WebStoreDAOException e){
			e.printStackTrace();
			assertTrue(e.getMessage(), true);
		}
	}
	
	// ########### After tests #######################
	
	@After
	/**
	 * Check that no data was left in the shopping cart
	 */
	public void aferTestShoppingCartTests()	{
		
		// Check that no Item were left in the shopping cart 
		assertTrue(sc.toString(), sc.getSize()==0);
	}
	
	
	// ########### Basic tests #######################
	
	@Test
	/**
	 * test basic insert product into the shopping cart
	 */
	public void testAddProductBasic() {
		Product testedProducted = products.get(random.nextInt(products.size()));
		testAddProductBasic(testedProducted);
		
		// initialize the shopping cart
		sc = new ShoppingCart();
	}
	
	/**
	 * test basic remove product into the shopping cart
	 */
	@Test
	public void testRemoveProductBasic() {
		Product testedProducted = products.get(random.nextInt(products.size()));
		testAddProductBasic(testedProducted);
		testRemoveProductBasic(testedProducted);
	}
	
	// ############ Intensive tests ###############
	
	@Test
	/**
	 * Test intensive insertion to the shopping cart
	 */
	public void testAddProductIntensiveNoRepetitions () {
		int size = products.size();
		int[] productsIndex = Utils.getRandomArrayNoRepetitions(size);
		
		for (int i : productsIndex)	{
			Product testedProducted = products.get(i);
			testAddProductBasic(testedProducted);
		}
		
		// initialize the shopping cart
		sc = new ShoppingCart();
	}
	
	@Test
	/**
	 * Test intensive delete from the shopping cart
	 * The difference Between Serial to Random is that 
	 * in Serial every product is insert and get deleted before
	 * inserting a new product and in Random all the product are 
	 * inserting first and then randomly being deleted
	 */
	public void testRemoveProductIntensiveNoRepetitionsSerial() {
		int size = products.size();
		int[] productsIndex = Utils.getRandomArrayNoRepetitions(size);
		
		for (int i : productsIndex)	{
			Product testedProducted = products.get(i);
			testAddProductBasic(testedProducted);
			testRemoveProductBasic(testedProducted);
		}
	}
	
	@Test
	/**
	 * Test intensive delete from the shopping cart
	 * The difference Between Serial to Random is that 
	 * in Serial every product is insert and get deleted before
	 * inserting a new product and in Random all the product are 
	 * inserting first and then randomly being deleted
	 */
	public void testRemoveProductIntensiveNoRepetitionsRandom() {
		int size = products.size();
		int[] productsIndex = Utils.getRandomArrayNoRepetitions(size);
		
		for (int i : productsIndex)	
			testAddProductBasic(products.get(i));
		
		productsIndex = Utils.getRandomArrayNoRepetitions(size);
		for (int i : productsIndex)	
			testRemoveProductBasic(products.get(i));
		
		// test that no product were left in the shopping cart
		assertTrue(sc.toString(), sc.getSize()==0);
	}
	
	/**
	 * add a new product to the shopping cart
	 * @param testedProducted the product to insert
	 */
	private void testAddProductBasic(Product testedProducted)	{		
		try {			
			sc.addProduct(testedProducted);
			sc.getProductShoppingCartRow(testedProducted);
		} catch (ShoppingCartException e) {
			e.printStackTrace();
			assertTrue(e.getMessage(), true);
		} 
	}
	
	/**
	 * remove a given product from the shopping cart
	 * this function assumes that there is only one product
	 * in the shopping cart
	 * @param testedProducted the product to insert
	 */
	private void testRemoveProductBasic(Product testedProducted) {
		int productCount = 0;
		// here the product is still in the shopping cart
		try {
			productCount = sc.getProductShoppingCartRow(testedProducted).getNumberOfproducts();
			sc.removeProduct(testedProducted);
		} catch (ShoppingCartException e) {
			e.printStackTrace();
			assertTrue(e.getMessage(), true);
		} 	
		
		// now the product has been deleted
		if (productCount == 1)	{
			try	{
				sc.getProductShoppingCartRow(testedProducted);
				assertTrue("Tested Product was not deleted", productCount==1);			
			}	catch (ShoppingCartException e) {
				// We are expecting to have an exception since the product count was 1
			}
			
			try	{
				sc.removeProduct(testedProducted);			
			}	catch (ShoppingCartException e) {
				// We are expecting to have an exception since the product count was 1
			}
		}	else	{
			try {
				assertTrue("Count of the product was not updated", productCount == sc.getProductShoppingCartRow(testedProducted).getNumberOfproducts()+1);
			} catch (ShoppingCartException e) {
				e.printStackTrace();
				assertTrue(e.getMessage(), true);
			}
		}
	}
}
