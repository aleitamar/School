package il.ac.telhai.finalProject;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;

public class WebStoreHttpServlet extends HttpServlet {
	private static final long serialVersionUID = -8060931133910859939L;
	public static final String USER_COOKIE_NAME = "UserCookie";
	public static final String COMMENTS_ATTR = "comments";
	public static final String CART_ATTR = "shoppingCart";
	public static final String WEBAPP_NAME = "/finalProjectV1.0";
	
	private static Logger logger;
	static {
		System.out.println("in the static block");
		logger = Logger.getLogger("WebStoreHttpServletLogger");
		BasicConfigurator.configure();
		try {
			logger.addAppender(new FileAppender(new SimpleLayout(),	"C:\\Logger.txt"));
			logger.info("start logging");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String uri = request.getRequestURI();
		logger.info(" # # # # # # # # # # # # # # # # # # # # # # # # # # # ");
		logger.info("-I- New URI request(method post): " + uri);

		if (uri.equals(WEBAPP_NAME+"/academic/UserLogin/")) {
			login(request, response);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String uri = request.getRequestURI();
		logger.info(" # # # # # # # # # # # # # # # # # # # # # # # # # # # ");
		logger.info("-I- New URI request(method get): " + uri);

		if (uri.equals(WEBAPP_NAME+"/academic/sendRequestForProduct/")) {
			sendRequestForProduct(request, response);
		} else if (uri
				.equals(WEBAPP_NAME+"/academic/sendRequestForProducts/")) {
			sendRequestForProducts(request, response);
		} else if (uri.equals(WEBAPP_NAME+"/academic/addProduct/")) {
			addProduct(request, response);
		} else if (uri.equals(WEBAPP_NAME+"/academic/removeProduct/")) {
			removeProduct(request, response);
		} else if (uri.equals(WEBAPP_NAME+"/academic/showAllMyProducts/")) {
			showAllMyProducts(request, response);
		} else if (uri.equals(WEBAPP_NAME+"/academic/SignOut/")) {
			logout(request, response);
		} else if (uri.equals(WEBAPP_NAME+"/academic/HomePage/")) {
			redirectToHomepage(response);
		} else if (uri.equals(WEBAPP_NAME+"/academic/SignIn/")) {
			forwardToLoginPage(request, response);
		} else if (uri.equals(WEBAPP_NAME+"/academic/Camera/")) {
			getServletContext().getRequestDispatcher(
					"/examples/04-jquery-mobile.html").forward(request,
					response);
		} else {
			redirectToLoginPage(response);
		}
	}

	private void removeProduct(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		String attrMessage = "Product removed from cart";
		
		try {
			WebStoreDAO db = WebStoreDAO.getProductHybernateDAO();
			Integer productId = Integer.parseInt(request
					.getParameter("productId"));
			Product product = db.getProduct(productId);
			HttpSession session = request.getSession();
			ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute(CART_ATTR);
			if (shoppingCart == null) {
				attrMessage = "Empty Shopping Cart!";
				return;	// finally executes either way
			}
			shoppingCart.removeProduct(product);
		} catch (ShoppingCartException e) {
			attrMessage = "Failed to remove product!" + e.getMessage();
		} catch (WebStoreDAOException e) {
			e.printStackTrace();
			attrMessage = "DB exception " + e.getMessage();
		} catch (NumberFormatException nfe) {
			attrMessage = "Invalid product number entered!"  + nfe.getMessage();
		} finally {
			request.setAttribute(COMMENTS_ATTR, attrMessage);
			forwardToHomepage(request, response);
		}
	}

	private void forwardToHomepage(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		getServletContext().getRequestDispatcher("/home.jsp").forward(request,
				response);
	}

	private void redirectToHomepage(HttpServletResponse response)
			throws IOException, ServletException {
		response.sendRedirect(WEBAPP_NAME+"/home.jsp");
	}

	private void logout(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
		logger.info("user signout started");
		Cookie userCookie = getUserCookie(request);
		if (userCookie == null) {
			logger.info("Could not find USER_COOKIE_NAME("+USER_COOKIE_NAME+")");
			return;
		}
		userCookie.setMaxAge(0);
		userCookie.setPath("/");
		response.addCookie(userCookie);
		logger.info("user signout finished");			
		}
		finally {
			request.getSession().invalidate();
			redirectToHomepage(response);
			logger.info("user signout finished");			
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		logger.info(" # # # # # # # # # # # # # # # # # # # # # # # # # # # ");
		logger.info("-I- new login attempt");
		try {
			WebStoreDAO db = WebStoreDAO.getProductHybernateDAO();
			User loginUser = new User(request.getParameter("User"),
					request.getParameter("Password"));
			if (!db.getExistingUser(loginUser.getName())
					|| !db.validateUserPassword(loginUser)) {
				logger.error("ERROR: wrong user name or password");
				request.getSession().setAttribute(COMMENTS_ATTR,
						"incorrect  username or password");
				request.setAttribute(COMMENTS_ATTR,
						"incorrect  username or password");
				forwardToLoginPage(request, response);
				return;
			}

			// if we got here username and password are correct
			setUserCookie(request, response, loginUser.getName());
			redirectToHomepage(response);
		} catch (WebStoreDAOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

	private void redirectToLoginPage(HttpServletResponse response)
			throws IOException {
		response.sendRedirect(WEBAPP_NAME+"/academic/SignIn/");
	}

	private void forwardToLoginPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/login.jsp").forward(request,
				response);

	}

	private void addProduct(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String attrMessage = "Product added to cart!";
		try {
			WebStoreDAO db = WebStoreDAO.getProductHybernateDAO();
			Integer productId = Integer.parseInt(request
					.getParameter("productId"));
			Product product = db.getProduct(productId);
			HttpSession session = request.getSession();
			ShoppingCart shoppingCart = (ShoppingCart) session
					.getAttribute(CART_ATTR);
			if (shoppingCart == null) {
				shoppingCart = new ShoppingCart();
				shoppingCart.addProduct(product);
				session.setAttribute(CART_ATTR, shoppingCart);
			} else {
				shoppingCart.addProduct(product);
			}
		} catch (WebStoreDAOException e) {
			e.printStackTrace();
			attrMessage = e.getMessage();
		} catch (NumberFormatException nfe) {
			attrMessage = "Invalid product number entered!";
		} finally {
			request.setAttribute(COMMENTS_ATTR, attrMessage);
			forwardToHomepage(request, response);
		}
	}

	private void showAllMyProducts(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
			HttpSession session = request.getSession();
			ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute(CART_ATTR);
			if (shoppingCart == null) {
				request.setAttribute(COMMENTS_ATTR, "Cart is empty!");
				forwardToHomepage(request, response);
				return;
			}
			getServletContext().getRequestDispatcher("/shoppingCart.jsp").forward(request, response);
	}

	private void sendRequestForProduct(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		try {
			WebStoreDAO db = WebStoreDAO.getProductHybernateDAO();
			Integer productId = Integer.parseInt(request
					.getParameter("productId"));

			Product product = db.getProduct(productId);
			logger.info("-I- Product info: " + product.toString());
			request.setAttribute("product", product);
			getServletContext().getRequestDispatcher(WEBAPP_NAME+"/product.jsp").forward(request, response);
			return;
		} catch (WebStoreDAOException e) {
			e.printStackTrace();
			request.setAttribute(COMMENTS_ATTR, e.getMessage());
		} catch (NumberFormatException nfe) {
			request.setAttribute(COMMENTS_ATTR, "Invalid product number entered!");
		}
		forwardToHomepage(request, response);
	}

	private void sendRequestForProducts(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		try {
			WebStoreDAO db = WebStoreDAO.getProductHybernateDAO();
			logger.info("-I- Request for all products");
			List<Product> products = db.getProducts();
			logger.info("-I- Product info: " + products.toString());
			request.setAttribute("products", products);
			getServletContext().getRequestDispatcher("/products.jsp").forward(request, response);
		} catch (WebStoreDAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param request
	 *            HttpServletRequest object represent the request
	 * @return the cookie corresponding to the cookie represent the user
	 */
	private Cookie getUserCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (null != cookies)
			for (int i = 0; i < cookies.length; i++)
				if (cookies[i].getName().equals(USER_COOKIE_NAME))
					return (Cookie) cookies[i];
		return null;
	}

	/**
	 * 
	 * @param request
	 *            HttpServletRequest object represent the request
	 * @param response
	 *            HttpServletResponse object represent the response
	 * @param loginName
	 *            the login name to assign to the cookie
	 * @return true on success else false when the cookie already were assigned
	 */
	private boolean setUserCookie(HttpServletRequest request,
			HttpServletResponse response, String loginName) {
		logger.info("setUserCookie started");
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++)
			if (cookies[i].getName().equals(USER_COOKIE_NAME)) {
				if (cookies[i].getValue().equals(loginName)) {
					logger.info("setUserCookie finished - cookie already exists");
				} else {
					logger.info("setUserCookie finished - username has been modified");
					cookies[i].setValue(loginName);
				}
				return false;
			}
		// make sure we are not inserting this cookie again
		Cookie cookie = new Cookie(USER_COOKIE_NAME, loginName);
		cookie.setPath("/");
		cookie.setMaxAge(60 * 60 * 24 * 7); // one hour
		response.addCookie(cookie);
		logger.info("setUserCookie finished - new cookie has been added");
		return true;
	}
}
