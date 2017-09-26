package secureBanking.web;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AcessPIIController {

    private static final Logger logger = Logger.getLogger(HomeController.class);

    @RequestMapping("/pii")
    public String welcome(Model model) {
        logger.debug("Home Page requested!");
        model.addAttribute("greeting", "gretting!!!");
        model.addAttribute("tagline", "PII Controller");
        
        return "welcome";
    }
    
}