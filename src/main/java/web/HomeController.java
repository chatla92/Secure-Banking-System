package web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import security.DataException;

@Controller
public class HomeController {
	private static final Logger logger = Logger.getLogger(HomeController.class);

	String role;
	int id;
	String name;

	public void setUserDetails(HttpServletRequest request) throws DataException {
		try {
			role = (String) request.getSession(false).getAttribute("role");
			id = (Integer) request.getSession(false).getAttribute("id");
			name = (String) request.getSession(false).getAttribute("name");
		} catch (NullPointerException e) {
			throw new DataException("Login failed");
		}
	}

	@RequestMapping({ "/", "/home" })
	public String welcome(Model model, HttpServletRequest request) {
		String retPage = "redirect:/login";
		HttpSession mSession = request.getSession(false);
		if (mSession != null) {
			try {
				setUserDetails(request);
			} catch (DataException e) {
				return retPage;
			}
			String name = (String) mSession.getAttribute("name");
			String role = (String) mSession.getAttribute("role");
			if(role.equals("Ind")||role.equals("MR")) {
				model.addAttribute("dis1", "display:None");
				model.addAttribute("dis2", "display:None");	
			} else {
				model.addAttribute("dis1", "display:block");
				model.addAttribute("dis2", "position: fixed; right: 10px; top: 5px");	
			}
			model.addAttribute("greeting", name);
			model.addAttribute("tagline", "Home Controller");
			retPage = "welcome";
		} else {
			retPage = "redirect:/login";
		}
		return retPage;
	}
}