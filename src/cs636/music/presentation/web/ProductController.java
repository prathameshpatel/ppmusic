package cs636.music.presentation.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cs636.music.domain.Product;
import cs636.music.service.ServiceException;
import cs636.music.service.UserServiceAPI;
import cs636.music.service.data.UserData;

public class ProductController implements Controller{

	private String view;
	private UserServiceAPI userService;
	
	public ProductController(UserServiceAPI userService, String view) {
		System.out.println("setting userService in ProductController (isnull = "+ (userService == null));
		this.userService = userService;
		this.view = view;
	}
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		System.out.println("in ProductController (isnull = " + (userService == null));
		HttpSession session = request.getSession();
		UserData userData = (UserData) session.getAttribute("user");
		//String nextURL = null;
		String prodCode = request.getParameter("prodCode");
		try {
			System.out.println("Getting product from ProductController");
			
			Product product = userService.getProduct(prodCode);
			/*if(userData.getEmailAddress() == null) {
				nextURL = "/register.html";
			}
			else {
				nextURL = "/listen.html";
			}*/
			request.setAttribute("prod", product);
			//request.setAttribute("nextURL", nextURL);
			session.setAttribute("prod", product);
			session.setAttribute("user", userData);
		}
		catch(ServiceException e) {
			System.out.println("UserController: " + e);
			throw new ServletException(e);
		}
		return view;
	}

}
