package il.ac.telhai.finalProject;

import java.util.List;

public interface IWebStoreDAO<T> {
	Product getProduct(Integer id) throws WebStoreDAOException;
	List<T> getProducts() throws WebStoreDAOException;
	void addProduct(Product p) throws WebStoreDAOException;
	void deleteProduct(Product obj) throws WebStoreDAOException;
	void deleteProduct(Integer id) throws WebStoreDAOException;
	boolean getExistingUser(String username) throws WebStoreDAOException;
	boolean validateUserPassword(User user) throws WebStoreDAOException;
	void addUser(User user) throws WebStoreDAOException;
	void removeUser(User user) throws WebStoreDAOException;
	
}

