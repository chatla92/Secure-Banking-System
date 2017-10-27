package web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hibernateModel.Transaction;
import security.DataException;
import security.ModelManager;
import util.RoleCheck;

@Controller
public class OTPController {

    String role;
    int id;
    String name;

    public static String transType;
    public static float amount;
    public static String fromAccNo;
    public static String toAccNo;
    public static int initBy;

    public void setUserDetails(HttpServletRequest request) throws DataException {
        try {
            role = (String) request.getSession(false).getAttribute("role");
            id = (Integer) request.getSession(false).getAttribute("id");
            name = (String) request.getSession(false).getAttribute("name");
        } catch (NullPointerException e) {
            throw new DataException("Login failed");
        }
    }

    @RequestMapping(value = "/otp", method = RequestMethod.GET)
    public String authorize(Model model, HttpServletRequest request) {
        if (request.getSession(false) != null) {
            try {
                setUserDetails(request);
            } catch (DataException e) {
                return "redirect:/login";
            }
            if (RoleCheck.isExternal(role)) {
                util.OTP.GenerateOTP(id);
                return "otp";
            }
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/otp", method = RequestMethod.POST)
    public String authorizePost(Model model, HttpServletRequest request, RedirectAttributes redAttr) {
        if (request.getSession(false) != null) {
            try {
                setUserDetails(request);
            } catch (DataException e) {
                return "redirect:/login";
            }
            if (transType!=null&&fromAccNo!=null&&toAccNo!=null) {
                if (RoleCheck.isExternal(role)) {
                    if (util.OTP.VerifyOPT(Integer.valueOf(request.getParameter("otp")))) {
                        Transaction transaction = ModelManager.createTransEntry(amount, transType, fromAccNo, toAccNo,true, false, initBy);
                        ModelManager.createInternalAuthEntry(transaction);
                        redAttr.addFlashAttribute("home", "OTP verification successful");
                    } else {
                        redAttr.addFlashAttribute("home", "OTP Verification failed, Please Initiate a new transfer");
                    }
                    transType=null;
                    fromAccNo=null;
                    toAccNo=null;
                    amount=0;
                    initBy=0;
                    TransferController.OTPrequired=false;
                    return "redirect:/home";
                }
            }
        }
        return "redirect:/login";
    }
}