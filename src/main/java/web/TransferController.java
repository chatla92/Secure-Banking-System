package web;

import java.util.ArrayList;

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
public class TransferController {
	String role;
	int id;
	String name;
	public static ArrayList<ArrayList<String>> list;

	public void setUserDetails(HttpServletRequest request) throws DataException {
		try {
			role = (String) request.getSession(false).getAttribute("role");
			id = (Integer) request.getSession(false).getAttribute("id");
			name = (String) request.getSession(false).getAttribute("name");
		} catch (NullPointerException e) {
			throw new DataException("Login failed");
		}
	}

	@RequestMapping(value = "/transfer", method = RequestMethod.POST)
	public String transfer(Model model, HttpServletRequest request, RedirectAttributes redAttr) {
		if (request.getSession() != null) {
			try {
				setUserDetails(request);
			} catch (DataException e) {
				e.printStackTrace();
			}
			boolean isSuccess = false;
			if (RoleCheck.isExternal(role)) {
				if (request.getParameter("action").equals("transfer")) {
					String email = request.getParameter("email");
					float amount = Float.valueOf(request.getParameter("amount"));
					String[] fromDetail = request.getParameter("fromAcc").split(",");
					if (!email.equals("") && amount >= 0) {
						isSuccess = ModelManager.handleExtTransfer(email, amount, fromDetail[1], fromDetail[0]);
					}
				}
			}
			redAttr.addFlashAttribute("home", isSuccess ? "Transfer successful" : "Transfer is unsuccessful");
		}
		return "redirect:/home";
	}
}