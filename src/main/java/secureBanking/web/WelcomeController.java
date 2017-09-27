package secureBanking.web;
import secureBanking.ORM.Entity.InternalUser;
import secureBanking.ORM.Impl.InternalUserImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/createUsers")
    public String createUsers(Map<String, Object> model)
    {
        try{
            InternalUser internalUser=new InternalUser(1059096,"software@asu.edu");
            internalUserImpl.save(internalUser);
            for (InternalUser q : internalUserImpl.findByEmail("pragna@asu.edu")) {
                System.out.println("helloworld"+q.toString());
            }
            model.put("message","User created");
        }
        catch(Exception e) {
            model.put("message","Error in creating user");
            return "messageError adding user";
        }

        return "createUsers";
    }

    @Autowired
    public InternalUserImpl internalUserImpl;
}
