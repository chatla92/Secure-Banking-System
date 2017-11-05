package web;

import javax.servlet.http.HttpServletRequest;

import hibernateModel.InternalUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import security.DataException;
import security.ModelManager;
import util.RoleCheck;
import org.apache.log4j.Logger;


@Controller
public class AcessPIIController {

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

	@RequestMapping(value="/pii" , method = RequestMethod.POST)
	public String pii(Model model, HttpServletRequest request,RedirectAttributes redAttr) {

		if (request.getSession(false) != null) {
			try {
				setUserDetails(request);
				if (!RoleCheck.isInternal(role)) {
					redAttr.addFlashAttribute("home", "Unauthorized access, Action will be reported");
					return "redirect:/home";
				}
			} catch (DataException e) {
				return "redirect:/login";
			}

			String unmaskedSSN = "";
			
			unmaskedSSN = ModelManager.getPII(Integer.parseInt(request.getParameter("id")), Boolean.parseBoolean(request.getParameter("customer")));
			if(!unmaskedSSN.equals("")){
				model.addAttribute("SSN", "SSN for above customer "+unmaskedSSN.split(",")[0]);
				model.addAttribute("name", unmaskedSSN.split(",")[1]);
			}
			else{
				model.addAttribute("SSN", "User Doesn't Exist");
				model.addAttribute("name","");
			}
			return "pii";

		}
        model.addAttribute("unmasked_SSN", "User not available");
		return "pii";
	}

	@RequestMapping(value="/employees" , method = RequestMethod.GET)
	public String getEmployee(Model model, HttpServletRequest request,RedirectAttributes redAttr) {

		if (request.getSession(false) != null) {
			try {
				setUserDetails(request);
				if (!RoleCheck.isInternal(role)) {
					redAttr.addFlashAttribute("home", "Unauthorized access, Action will be reported");
					return "redirect:/home";
				}
			} catch (DataException e) {
				return "redirect:/login";
			}
			model.addAttribute("emplist", ModelManager.getInternalUserList());
			return "emp_list";
		}
		return "redirect:/login";
	}

}