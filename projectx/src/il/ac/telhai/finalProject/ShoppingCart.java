package il.ac.telhai.finalProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public class ShoppingCart {
	
	public final static String ProductShoppingCartNotFound = "Product shopping cart not found";
	public final static String ProductNotFound = "Product not found";
	
	private static Logger logger;
	static {
		System.out.println("in the static block");
		logger = Logger.getLogger("WebStoreHttpServletLogger");
		BasicConfigurator.configure();
        try
        {
        	String path = Utils.getFullPath(Class.forName("il.ac.telhai.finalProject.ShoppingCart")) + ".log";
            logger.addAppender(new FileAppender(new SimpleLayout(), path));
            logger.info("start logging");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private List<ShoppingCartRow> shoppingCart;
	
	public ShoppingCart()	{
		shoppingCart = new ArrayList<ShoppingCartRow>();
	}
	
	public List<ShoppingCartRow> getShoppingCart() {
		return shoppingCart;
	}

	public void addProduct(Product product) {
		for (ShoppingCartRow s : shoppingCart)	{
			if (s.getproduct().equals(product))	{
				s.increaseNumberOfproducts();
				return;
			}
		}
		shoppingCart.add(new ShoppingCartRow(product, 1));
	}
	
	public void removeProduct(Product product) throws ShoppingCartException	{
		for (ShoppingCartRow s : shoppingCart)	{
			if (s.getproduct().equals(product))	{
				if (s.getNumberOfproducts()==1){
					shoppingCart.remove(s);
					return;
				}	else	{
					s.decreaseNumberOfproducts();
				}
			}
		}
		throw new ShoppingCartException(ProductNotFound);
	}
	
	public ShoppingCartRow getProductShoppingCartRow(Product product) throws ShoppingCartException	{
		for (ShoppingCartRow s : shoppingCart)	{
			if (s.getproduct().equals(product))	{
				return s;
			}
		}
		throw new ShoppingCartException(ProductShoppingCartNotFound);
	}
	
	public int getSize()	{
		return shoppingCart.size();
	}
	
	public String toString()	{
		StringBuffer sb = new StringBuffer();
		
		for (ShoppingCartRow s : shoppingCart)	{
			sb.append(s.toString());
		}
		
		return sb.toString();
	}
}
