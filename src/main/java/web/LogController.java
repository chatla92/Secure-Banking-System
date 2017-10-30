package web;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import security.DataException;
import security.ModelManager;
import util.RoleCheck;

@Controller
public class LogController {

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

    @RequestMapping(value = "/log", method = RequestMethod.GET)
    public String welcome(Model model, HttpServletRequest request, RedirectAttributes redAttr) {
        if (request.getSession(false) != null) {
            try {
                setUserDetails(request);
                if (role.equals("IA")) {
                    model.addAttribute("transactionlist", ModelManager.getAllTrasactions());
                    return "log";
                } else {
                    redAttr.addFlashAttribute("home", "You don't have the previlege to view transactions");
                }
            } catch (DataException e) {
                redAttr.addFlashAttribute("home", "Failed to fetch transactions");
            }
            return "redirect:/home";
        }
        redAttr.addFlashAttribute("home", "Illegal Activity detected. Incident will reported");
        return "redirect:/login";
    }
}