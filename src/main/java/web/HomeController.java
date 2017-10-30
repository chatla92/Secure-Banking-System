package web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import security.DataException;
import security.ModelManager;
import security.ModelUtil;

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
			model.addAttribute("greeting", name);
			model.addAttribute("tagline", "Home Controller");
			if (role.equals("Ind") || role.equals("MR")) {

				model.addAttribute("AccountList", ModelManager.getAccountList(id));
				return role.equals("Ind") ? "individual" : "merchant";
			} else {
			    if(role.equals("IA")){
			        return "admin";
			    }
				model.addAttribute("tier", role.equals("Tier1") ? "None" : "Block");
				return "tier1";
			}

		} else {
			return "redirect:/login";
		}
	}
}