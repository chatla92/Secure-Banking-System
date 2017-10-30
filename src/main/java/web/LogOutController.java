package web;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LogOutController {
	@RequestMapping(value = "/logout")

	public String logout(HttpServletRequest request, RedirectAttributes redAttr) {
		if (request.getSession() != null)
			request.getSession(false).invalidate();
		redAttr.addFlashAttribute("flash", "logged out successfully!!");
		return "redirect:/login";
	}
}