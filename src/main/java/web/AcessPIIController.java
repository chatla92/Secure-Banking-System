package web;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import security.DataException;

@Controller
public class AcessPIIController {
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

	@RequestMapping("/pii")
	public String welcome(Model model, HttpServletRequest request) {
		if (request.getSession(false) != null) {
			try {
				setUserDetails(request);
			} catch (DataException e) {
				return "redirect:/login";
			}
			model.addAttribute("greeting", "gretting!!!");
			model.addAttribute("tagline", "Delete Controller");
			return "pii";
		}
		return "redirect:/login";
	}

}