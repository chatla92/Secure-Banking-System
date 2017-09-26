package secureBanking.web;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthorizeController {

    private static final Logger logger = Logger.getLogger(HomeController.class);

    @RequestMapping("/authorize")
    public String welcome(Model model) {
        logger.debug("Home Page requested!");
        model.addAttribute("greeting", "gretting!!!");
        model.addAttribute("tagline", "Authorize Controller");
        
        return "welcome";
    }
}