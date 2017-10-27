package web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import security.DataException;
import security.ModelManager;
import util.RoleCheck;

@Controller
public class AuthorizeController {

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

	@RequestMapping(value = "/authorize/request", method = RequestMethod.GET)
	public String authorize(Model model, HttpServletRequest request) {
		if (request.getSession(false) != null) {
			try {
				setUserDetails(request);
			} catch (DataException e) {
				return "redirect:/login";
			}
			if (RoleCheck.isExternal(role)) {
				model.addAttribute("PendingList", ModelManager.getAuthRequests(id));
				return "authorize_trans";
			}
		}
		return "redirect:/login";
	}

	@RequestMapping(value = "/authorize/request", method = RequestMethod.POST)
	public String authorizePost(Model model, HttpServletRequest request, RedirectAttributes redAttr) {
		if (request.getSession(false) != null) {
			try {
				setUserDetails(request);
			} catch (DataException e) {
				return "redirect:/login";
			}
			if (RoleCheck.isExternal(role)) {
				redAttr.addFlashAttribute("flash", ModelManager.handleExtRequestApprove(request.getParameter("id"),
						request.getParameter("action").equals("approved"), id));
				return "redirect:/authorize/request";
			}
		}
		return "redirect:/login";
	}

	@RequestMapping(value = "/authorize/modify", method = RequestMethod.GET)
	public String authorizeModify(Model model, HttpServletRequest request) {
		if (request.getSession(false) != null) {
			try {
				setUserDetails(request);
			} catch (DataException e) {
				return "redirect:/login";
			}
			if (RoleCheck.isInternal(role)) {
				model.addAttribute("modifylist", ModelManager.getModifyRequests());
				return "authorize_modify";
			}
		}
		return "redirect:/login";
	}

	@RequestMapping(value = "/authorize/modify", method = RequestMethod.POST)
	public String authorizeModifyPost(Model model, HttpServletRequest request, RedirectAttributes redAttr) {
		if (request.getSession(false) != null) {
			try {
				setUserDetails(request);
			} catch (DataException e) {
				return "redirect:/login";
			}
			if (RoleCheck.isInternal(role)) {
				redAttr.addFlashAttribute("flash", ModelManager.handleIntModifyApprove(request.getParameter("modify"),
						request.getParameter("action").equals("approve")));
				return "redirect:/authorize/modify";
			}
		}
		return "redirect:/login";
	}

	@RequestMapping(value = "/authorize/critical", method = RequestMethod.GET)
	public String authorizeCritical(Model model, HttpServletRequest request) {
		if (request.getSession(false) != null) {
			try {
				setUserDetails(request);
			} catch (DataException e) {
				return "redirect:/login";
			}
			if (role.equalsIgnoreCase("Tier2")) {
				model.addAttribute("criticalList", ModelManager.getCriticalTxs());
				return "authorize_critical";
			}
		}
		return "redirect:/login";
	}

	@RequestMapping(value = "/authorize/critical", method = RequestMethod.POST)
	public String authorizeCriticalPost(Model model, HttpServletRequest request, RedirectAttributes redAttr) {
		if (request.getSession(false) != null) {
			try {
				setUserDetails(request);
			} catch (DataException e) {
				return "redirect:/login";
			}
			if (role.equalsIgnoreCase("Tier2")) {
				redAttr.addFlashAttribute("flash", ModelManager.handleCriticalTxs(request.getParameter("id"),
						request.getParameter("action").equals("approve"), id));
				return "redirect:/authorize/critical";
			}
		}
		return "redirect:/login";
	}
}