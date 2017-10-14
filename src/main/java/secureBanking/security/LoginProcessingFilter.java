//package secureBanking.security;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//
//public class LoginProcessingFilter extends AbstractAuthenticationProcessingFilter {
//
//    public LoginProcessingFilter(String defaultFilterProcessesUrl) {
//        super(defaultFilterProcessesUrl);
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request,
//                                                HttpServletResponse response)
//            throws AuthenticationException, IOException, ServletException
//    {
//        if (request.getMethod().equals(HttpMethod.POST.name()))
//        {
//            return new UsernamePasswordAuthenticationToken(request.getParameter("username"), request.getParameter("password"));
//        }
//        return null;
//    }
//}
