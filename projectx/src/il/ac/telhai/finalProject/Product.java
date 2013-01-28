package il.ac.telhai.finalProject;

public class Product {
	private String name;
	private double price;
	private int id;
	
	public Product() 
	{
		super();
	}
	
	public Product(String name, double price )	{
		setName(name);
		setPrice(price);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Product) {
			return ((Product) obj).getId() == this.getId();
		}
		return false;
	}
	
	public String toString(){
		return "Name: " + name + " Price: " + price + " ID: " + id;
	}	

}
