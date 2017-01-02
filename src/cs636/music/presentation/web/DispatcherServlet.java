package cs636.music.presentation.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs636.music.config.MusicSystemConfig;
import cs636.music.service.UserServiceAPI;

public class DispatcherServlet extends HttpServlet{

	private static UserServiceAPI userService;
	private static int quantity;
	
	// The controllers, roughly one per student page or form
	private static Controller userController;
	private static Controller catalogController;
	private static Controller productController;
	private static Controller listenController;
	private static Controller registrationController;
	private static Controller cartController;
	
	private static final String WELCOME_URL = "/welcome.html";
	private static final String WELCOME_VIEW = "/welcome.jsp";
	private static final String USER_WELCOME_URL = "/userWelcome.html";
	private static final String USER_WELCOME_VIEW = "/WEB-INF/jsp/userWelcome.jsp";
	private static final String CATALOG_URL = "/catalog.html";
	private static final String CATALOG_VIEW = "/WEB-INF/jsp/catalog.jsp";
	private static final String PRODUCT_URL = "/product.html";
	private static final String PRODUCT_VIEW = "/WEB-INF/jsp/product.jsp";
	private static final String CART_URL = "/cart.html";
	private static final String CART_VIEW = "/WEB-INF/jsp/cart.jsp";
	private static final String LISTEN_URL = "/listen.html";
	private static final String LISTEN_VIEW = "/WEB-INF/jsp/listen.jsp";
	private static final String REGISTER_VIEW = "/WEB-INF/jsp/register.jsp";
	private static final String REGISTER_URL = "/register.html";
	private static final String CHECKOUT_URL = "/register.html";
	private static final String CHECKOUT_VIEW = "/WEB-INF/jsp/checkout.jsp";
	
	@Override
	public void init() throws ServletException {
		System.out.println("Starting dispatcher servlet initialization");
		try {
			MusicSystemConfig.configureServices();
		} catch (Exception e) {
			// log the error in tomcat's log
			System.out.println(MusicSystemConfig.exceptionReport(e));
			throw new ServletException(e); // fatal error
		}
		userService = MusicSystemConfig.getUserService();
		if (userService==null) {
			throw new ServletException("DispatcherServlet: bad initialization");
		}
		quantity = MusicSystemConfig.QUANTITY;
		// create all the controllers and their forward URLs
		userController = new UserController(USER_WELCOME_VIEW);
		catalogController = new CatalogController(userService, CATALOG_VIEW);
		productController = new ProductController(userService, PRODUCT_VIEW);
		listenController = new ListenController(userService, LISTEN_VIEW);
		registrationController = new RegistrationController(userService, REGISTER_VIEW, LISTEN_VIEW, CHECKOUT_VIEW);
		cartController = new CartController(userService, CART_VIEW, PRODUCT_VIEW, quantity);
		
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// get webapp-specific part of the URL, the part after
		// the context path that identifies the webapp
		String requestURL = request.getServletPath();
		System.out.println("DispatcherServlet: requestURL = " + requestURL);
		
		String forwardURL = null;
		if (requestURL.equals(WELCOME_URL)) {
			forwardURL = WELCOME_VIEW; // no controller needed
		}
		else if (requestURL.equals(USER_WELCOME_URL)) {
			forwardURL = USER_WELCOME_VIEW; //no controller needed
		}
		else if (requestURL.equals(CATALOG_URL)) {
			forwardURL = catalogController.handleRequest(request, response);
		}
		else if (requestURL.equals(PRODUCT_URL)) {
			forwardURL = productController.handleRequest(request, response);
		}
		else if (requestURL.equals(LISTEN_URL)) {
			forwardURL = listenController.handleRequest(request, response);
		}
		else if (requestURL.contains(REGISTER_URL)) {
			forwardURL = registrationController.handleRequest(request, response);
		}
		else if (requestURL.equals(CART_URL)) {
			forwardURL = cartController.handleRequest(request, response);
		}
		else {
			throw new ServletException("DispatcherServlet: Unknown servlet path: " + requestURL);
		}
		// Here with good forwardURL to forward to
		System.out.println("DispatcherServlet: forwarding to "+ forwardURL);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(forwardURL);
		dispatcher.forward(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	public void destroy() {
		System.out.println("DispatcherServlet: shutting down");
		MusicSystemConfig.shutdownServices();
	}
	
}
