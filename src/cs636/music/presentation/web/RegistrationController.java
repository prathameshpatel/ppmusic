package cs636.music.presentation.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cs636.music.service.ServiceException;
import cs636.music.service.UserServiceAPI;
import cs636.music.service.data.UserData;

public class RegistrationController implements Controller{

	private String view;
	private String lview;
	private String cview;
	private UserServiceAPI userService;
	
	public RegistrationController(UserServiceAPI userService,String view,String lview,String cview) {
		this.view = view;
		this.lview = lview;
		this.cview = cview;
		this.userService = userService;
	}
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		UserData userData = (UserData) session.getAttribute("user");
		String action = request.getParameter("action");
		System.out.println("action = "+action);
		String status = request.getParameter("status");
		System.out.println("status = "+status);
		
		if(action != null && action.equals("listen")) {
			
			String firstName = (String) request.getParameter("firstName");
			String lastName = (String) request.getParameter("lastName");
			String email = (String) request.getParameter("email");
			try {
				userService.registerUser(firstName, lastName, email);
				userData = userService.getUserInfo(email);
				session.setAttribute("user", userData);
			}
			catch(ServiceException e) {
				System.out.println("RegistrationController: " + e);
				throw new ServletException(e);
			}
			return lview;
		}
		else if(action != null && action.equals("checkout")) {
			String firstName = (String) request.getParameter("firstName");
			String lastName = (String) request.getParameter("lastName");
			String email = (String) request.getParameter("email");
			try {
				userService.registerUser(firstName, lastName, email);
				userData = userService.getUserInfo(email);
				session.setAttribute("user", userData);
			}
			catch(ServiceException e) {
				System.out.println("RegistrationController: " + e);
				throw new ServletException(e);
			}
			return cview;
		}
		
		
		return view;
		
	}

}
