package secureBanking.web;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private static final Logger logger = Logger.getLogger(HomeController.class);

    @RequestMapping({"/","/home"})
    public String welcome(Authentication context, Model model) {
        logger.debug("Home Page requested!");
        model.addAttribute("greeting", String.format("gretting %s!!!", context.getPrincipal()));
        model.addAttribute("tagline", "Home Controller");
        
        return "welcome";
    }
}