package web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import security.DataException;
import security.ModelManager;
import util.RoleCheck;

@Controller
public class ModifyController {

	private static final String LOGIN_FAILED = "Login failed";
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

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String welcome(Model model, HttpServletRequest request) {
		if (request.getSession(false) != null) {
			try {
				setUserDetails(request);
				if (RoleCheck.isExternal(role)) {
					Map<String, String> map = ModelManager.getModifiableData(id);
					model.addAllAttributes(map);
				} else {
					model.addAllAttributes(ModelManager.getExtUserInfo(request.getParameter("account")));
				}
			} catch (DataException e) {
				if (e.getMessageDetail().equals(LOGIN_FAILED))
					return "redirect:/login";
				else
					return "redirect:/home";
			}
			return "modify";
		}
		return "redirect:/login";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String welcomePost(Model model, HttpServletRequest request) {
		if (request.getSession(false) != null) {
			try {
				setUserDetails(request);
			} catch (DataException e) {
				return "redirect:/login";
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("username", request.getParameter("username"));
			map.put("address", request.getParameter("address"));
			map.put("phone", request.getParameter("phone"));
			map.put("zipcode", request.getParameter("zipcode"));
			map.put("email", request.getParameter("email"));
			map.put("gender", request.getParameter("gender"));
			ModelManager.setModifiableData(id, map);
			return "home";
		}
		return "redirect:/login";
	}
}