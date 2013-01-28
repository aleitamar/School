package il.ac.telhai.finalProject;


public class ShoppingCartRow {
	private int numberOfproducts;
	private Product product;
	
	public int getNumberOfproducts() {
		return numberOfproducts;
	}

	public void increaseNumberOfproducts(){
		numberOfproducts++;
	}
	
	public void decreaseNumberOfproducts(){
		numberOfproducts--;
	}
	
	public void setNumberOfproducts(int numberOfproducts) {
		this.numberOfproducts = numberOfproducts;
	}

	public Product getproduct() {
		return product;
	}

	public void setproduct(Product product) {
		this.product = product;
	}

	public ShoppingCartRow(Product product, int numberOfproducts)	{
		this.product = product;
		this.numberOfproducts = numberOfproducts;
	}
	
	public String toString()	{
		return "" + numberOfproducts + "\t" + product;
	}
	
}
