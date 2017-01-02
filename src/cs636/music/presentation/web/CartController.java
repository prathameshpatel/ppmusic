package cs636.music.presentation.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cs636.music.domain.Cart;
import cs636.music.domain.LineItem;
import cs636.music.domain.Product;
import cs636.music.service.ServiceException;
import cs636.music.service.UserServiceAPI;

public class CartController implements Controller{

	private String view;
	private String pview;
	private UserServiceAPI userService;
	private int quantity;
	
	public CartController(UserServiceAPI userService, String view, String pview,int quantity) {
		System.out.println("setting userService in CartController (isnull = "+ (userService == null));
		this.userService = userService;
		this.view = view;
		this.pview = pview;
		this.quantity = quantity;
	}
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		session.setAttribute("quantity", quantity);
		Cart cart = (Cart) session.getAttribute("cart");
		String action = request.getParameter("action");
		
		/*String addToCart = (String) request.getAttribute("addToCart");
		String showCart = (String) request.getAttribute("showCart");*/
		if(action != null) {
		if(action.equals("addToCart")) {
			Product product = (Product) session.getAttribute("prod");
			
				if(cart ==null){
					cart = userService.createCart();
				}
				if(product != null) {
					LineItem lineItem = new LineItem(product,1);
					cart.addItem(lineItem);
					session.setAttribute("cart", cart);
				}
			return pview;
		}
		else if(action.equals("update")) {
			int quant = Integer.parseInt(request.getParameter("quantity"));
			System.out.println(quant);
			try {
			LineItem lineItem = new LineItem(userService.getProduct(request.getParameter("code")),quant);
			
			
			lineItem.setQuantity(quant);
			cart = (Cart)session.getAttribute("cart");
			cart.addItem(lineItem);
			session.setAttribute("cart", cart);
			}
			catch(ServiceException e) {
				System.out.println("UserController: " + e);
				throw new ServletException(e);
			}
			return view;
		}
		else if(action.equals("remove")) {
			cart = (Cart)session.getAttribute("cart");
			try {
			Product product = userService.getProduct(request.getParameter("code"));
			cart.removeItem(product);
			session.setAttribute("cart", cart);
			}
			catch(ServiceException e) {
				System.out.println("UserController: " + e);
				throw new ServletException(e);
			}
			return view;
		}
	}
			return view;
	
	}

}
