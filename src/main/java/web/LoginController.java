package web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.annotations.DiscriminatorFormula;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import security.DataException;
import security.ModelManager;

@Controller
public class LoginController {

	private static final Logger logger = Logger.getLogger(LoginController.class);
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

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, HttpServletRequest request) {
		String retPage;
		try {
			if (request.getSession(false) != null && request.getSession().getAttribute("id") != null) {
				retPage = "redirect:/home";
			} else {
				retPage = "login";
			}
		} catch (NullPointerException e) {
			retPage = "login";
		}
		return retPage;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPost(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redAttr) {
		String name = request.getParameter("name");
		String pwd = request.getParameter("password");
		if (name != "" && request.getParameter("usertype").equals("i")) {
			try {
				Map<String, String> result = ModelManager.validateInternalUserById(name, pwd);
				logger.info("Result from DB is " + result);
				if (result != null) {
					// createsession
					session.setAttribute("role", result.get("role"));
					session.setAttribute("id", Integer.valueOf(result.get("id")));
					session.setAttribute("name", result.get("name"));
					return "redirect:/home";
				} else {
					logger.info("Flash ============ Unsuccesful Login");
					redAttr.addFlashAttribute("flash", "Unsuccesful Login");
				}
			} catch (DataException e) {
				logger.info(e.getMessageDetail());
				redAttr.addFlashAttribute("flash", e.getMessageDetail());
			}
		} else if (name != "" && request.getParameter("usertype").equals("e")) {
			try {
				Map<String, String> result = ModelManager.validateExternalUserById(name, pwd);
				if (result != null) {
					session.setAttribute("role", result.get("role"));
					session.setAttribute("id", Integer.valueOf(result.get("id")));
					session.setAttribute("name", result.get("name"));
					return "redirect:/home";
				} else {
					redAttr.addFlashAttribute("flash", "Unsuccesful Login");
				}
			} catch (DataException e) {
				redAttr.addFlashAttribute("flash", e.getMessageDetail());
			}
		}
		return "redirect:/login";
	}
}
