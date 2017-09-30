package web;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import manager.App;

@Controller
public class HomeController {
    private static final Logger logger = Logger.getLogger(HomeController.class);
    @RequestMapping({"/","/home"})
    
    public String welcome(Model model) {
        if(logger.isDebugEnabled()){
            logger.debug("Home Page requested!");
        }
        App app=new App();
        app.create();
        app.updateEmployee();
        model.addAttribute("greeting", "gretting!!!");
        model.addAttribute("tagline", "Home Controller");
        
        return "welcome";
    } 
}