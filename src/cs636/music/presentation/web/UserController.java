package cs636.music.presentation.web;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cs636.music.domain.Product;
import cs636.music.service.UserServiceAPI;
import cs636.music.service.data.UserData;
import cs636.music.service.ServiceException;

public class UserController implements Controller{

	private String view;
	
	public UserController(String view) {

		this.view = view;
	}
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		
		/*UserData userData = (UserData) session.getAttribute("user");
		if (userData == null) {
			userData = new UserData();
			session.setAttribute("user", userData);
		}*/
		return view;
	}

	
}
