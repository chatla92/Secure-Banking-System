package web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import security.DataException;
import security.HashingMasking;
import security.ModelManager;
import util.RoleCheck;

@Controller
public class CreateController {
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

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Model model, HttpServletRequest request, RedirectAttributes redAttr) {
		if (request.getSession(false) != null) {
			try {
				setUserDetails(request);
				if(RoleCheck.isExternal(role)){
					redAttr.addFlashAttribute("home", "Unauthorized access, Action will be reported");
					logger.warn(name+ " has tried to access create page");
					return "redirect:/home";
				}
			} catch (DataException e) {
				return "redirect:/login";
			}
			model.addAttribute("greeting", "gretting!!!");
			model.addAttribute("tagline", "Delete Controller");
			return "create";
		}
		return "redirect:/login";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createPost(Model model, HttpServletRequest request, RedirectAttributes redAttr) {
		if (request.getSession(false) != null) {
			try {
				setUserDetails(request);
			} catch (DataException e) {
				return "redirect:/login";
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("name", request.getParameter("name"));
			map.put("password", HashingMasking.hashString(request.getParameter("password")));
			map.put("ssn", HashingMasking.mask(request.getParameter("ssn")));
			map.put("username", request.getParameter("username"));
			map.put("address", request.getParameter("address"));
			map.put("phone", request.getParameter("phone"));
			map.put("zipcode", request.getParameter("zipcode"));
			map.put("email", request.getParameter("email"));
			map.put("gender", request.getParameter("gender"));
			map.put("role", request.getParameter("role"));
			map.put("threshold", request.getParameter("threshold"));
			try {
				ModelManager.createData(map);
			} catch (DataException e) {
				redAttr.addFlashAttribute("flash", e.getMessageDetail());
				return "redirect:/create";
			}
			return "home";
		}
		return "redirect:/login";
	}
}