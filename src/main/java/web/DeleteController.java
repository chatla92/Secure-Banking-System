package web;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import security.DataException;
import security.ModelManager;

@Controller
public class DeleteController {
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

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String welcome(Model model, HttpServletRequest request, @RequestParam(value = "id", required = false) String account, RedirectAttributes redAttr) {
		if (request.getSession(false) != null) {
			try {
				setUserDetails(request);
			} catch (DataException e) {
				return "redirect:/login";
			}
			boolean isSuccessful =false;
			if(role.equals("Tier2")){
				isSuccessful=ModelManager.deleteExtUser(account);
			} else if(role.equalsIgnoreCase("IA")){
				isSuccessful=ModelManager.deleteIntUser(account);
			}else {
				redAttr.addFlashAttribute("home", "You don't have permission to carryout the action");
				return "redirect:/home";
			}
			redAttr.addFlashAttribute("home", isSuccessful? "Succesffully Deleted user":"User Does not exist");
			return "redirect:/home";
		}
		return "redirect:/login";
	}

}