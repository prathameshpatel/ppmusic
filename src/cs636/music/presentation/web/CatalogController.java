package cs636.music.presentation.web;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cs636.music.domain.Product;
import cs636.music.service.ServiceException;
import cs636.music.service.UserServiceAPI;
import cs636.music.service.data.UserData;

public class CatalogController implements Controller{

	private String view;
	private UserServiceAPI userService;
	
	public CatalogController(UserServiceAPI userService, String view) {
		System.out.println("setting userService in CatalogController (isnull = "
				+ (userService == null));
		this.userService = userService;
		this.view = view;
	}
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		System.out.println("in CatalogController (isnull = " + (userService == null));
		HttpSession session = request.getSession();
		UserData userData = (UserData) session.getAttribute("user");
		try {
			System.out.println("Getting product list from catalogController");
			Set<Product> prodSet = userService.getProductList();
			request.setAttribute("prodList", prodSet);
			session.setAttribute("user", userData);
		}
		catch(ServiceException e) {
			System.out.println("UserController: " + e);
			throw new ServletException(e);
		}
		
		return view;
	}

	
}
