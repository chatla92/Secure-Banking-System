package web;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DeleteController {
    private static final Logger logger = Logger.getLogger(HomeController.class);
    @RequestMapping("/delete")
    
    public String welcome(Model model) {
        if(logger.isDebugEnabled()){
            logger.debug("Home Page requested!");
        }
        model.addAttribute("greeting", "gretting!!!");
        model.addAttribute("tagline", "Delete Controller");
        
        return "welcome";
    }
    
}