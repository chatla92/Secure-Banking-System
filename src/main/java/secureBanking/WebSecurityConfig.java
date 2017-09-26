package secureBanking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import secureBanking.security.LoginAuthenticationProvider;
import secureBanking.security.LoginProcessingFilter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${app.auth.endpoint}") public String AUTH_END_POINT;
    @Value("${app.auth.logout.endpoint}") public String LOGOUT_END_POINT;
    @Value("${app.auth.form.endpoint}") public String AUTH_FORM;
    @Value("${app.welcome.endpoint}") public String WELCOME_END_POINT;

    private LoginAuthenticationProvider loginAuthenticationProvider;

    @Autowired
    public WebSecurityConfig(LoginAuthenticationProvider loginAuthenticationProvider) {
        this.loginAuthenticationProvider = loginAuthenticationProvider;
    }

    private LoginProcessingFilter buildLoginProcessingFilter()
    {
        return new LoginProcessingFilter(AUTH_END_POINT);
    }

    @Bean
    public AuthenticationManager getAuthenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(loginAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .exceptionHandling()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint(AUTH_FORM))

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)

                .and()
                .formLogin()
                .loginPage(AUTH_FORM).permitAll()

                .and()
                .logout()
                .logoutUrl(LOGOUT_END_POINT)
                .invalidateHttpSession(true)
                .logoutSuccessUrl(WELCOME_END_POINT)

                .and()
                .authorizeRequests()
                .antMatchers(AUTH_END_POINT).permitAll()
                .antMatchers(WELCOME_END_POINT).permitAll()

                .and()
                .authorizeRequests()
                .antMatchers("/log").hasRole("ADMIN")

                .and()
                .authorizeRequests()
                .antMatchers("/**").authenticated()

                .and()
                .addFilterBefore(buildLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
