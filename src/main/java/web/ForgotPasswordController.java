package web;

import javax.servlet.http.HttpServletRequest;

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
public class ForgotPasswordController {

    public static String password;
    public static String email;

    @RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
    public String authorize(Model model, HttpServletRequest request) {
        return "forgotpass";

    }

    @RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
    public String authorizePost(Model model, HttpServletRequest request, RedirectAttributes redAttr) {

        if (!request.getParameter("email").equals("") && !request.getParameter("new_pass").equals("")) {
            email=request.getParameter("email");
            util.OTP.GenerateOTP(request.getParameter("email"));
            password = HashingMasking.hashString(request.getParameter("new_pass"));
            return "otpPass";
        }
        redAttr.addFlashAttribute("flash", "Email or password cannot empty");
        return "redirect:/forgotpassword";
    }

    @RequestMapping(value = "/forgototp", method = RequestMethod.POST)
    public String forgotOTP(Model model, HttpServletRequest request, RedirectAttributes redAttr) {
        if (!request.getParameter("otp").equals("")) {
            if (util.OTP.VerifyOPT(Integer.valueOf(request.getParameter("otp")))) {
                ModelManager.changePassword(email,password);
                redAttr.addFlashAttribute("home", "Password Changed Successfully");
            } else {
                redAttr.addFlashAttribute("home", "Password change fail");
            }
            password="";
            return "redirect:/login";
        }
        redAttr.addFlashAttribute("home", "Try again");
        return "redirect:/login";
    }
}