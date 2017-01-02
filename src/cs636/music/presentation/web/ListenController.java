package cs636.music.presentation.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cs636.music.domain.Product;
import cs636.music.service.UserServiceAPI;

public class ListenController implements Controller{

	private String view;
	private UserServiceAPI userService;
	
	public ListenController(UserServiceAPI userService, String view) {
		System.out.println("setting userService in ListenController (isnull = "
				+ (userService == null));
		this.userService = userService;
		this.view = view;
	}
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		System.out.println("in ListenController (isnull = " + (userService == null));
		HttpSession session = request.getSession();
		Product product = (Product) session.getAttribute("prod");
		//UserBean userBean = (UserBean) session.getAttribute("user");
		//UserData userData = (UserData) session.getAttribute("user");
			request.setAttribute("prod", product);
		
		return view;
	}

}
