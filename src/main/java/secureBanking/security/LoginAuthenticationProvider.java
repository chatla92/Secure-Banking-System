package secureBanking.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Map<String, String> userMap = new HashMap<String, String>();
        userMap.put("admin","admin@123");
        userMap.put("user", "user@123");

        Map<String, List<GrantedAuthority>> userAuthorities= new HashMap<String, List<GrantedAuthority>>();
        userAuthorities.put("admin",
                            new ArrayList<GrantedAuthority>()
                            {{
                                    add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                                    add(new SimpleGrantedAuthority("ROLE_SUPPORT"));
                            }});
        userAuthorities.put("user",
                            new ArrayList<GrantedAuthority>()
                            {{
                                    add(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
                            }});

        if (userMap.containsKey(authentication.getPrincipal()) &&
                (userMap.get(authentication.getPrincipal()).equals(authentication.getCredentials()) || authentication.isAuthenticated()))
        {
            return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
                                                           authentication.getCredentials(),
                                                           userAuthorities.get(authentication.getPrincipal()));
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
