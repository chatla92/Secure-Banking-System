package secureBanking.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;


@Controller
public class WelcomeController {

    @RequestMapping("/welcome")
    public String index(Map<String, Object> model) {
        model.put("message","hello wor1d! ");
        return "index";
    }

    @GetMapping("/login-page")
    public String login() {
        return "login";
    }
}
