package by.morunov.security;

import by.morunov.domain.entity.Role;
import by.morunov.domain.entity.User;
import by.morunov.properties.CorsProperties;
import by.morunov.service.impl.UserServiceImpl;
import by.morunov.util.CustomBasicAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;


/**
 * @author Alex Morunov
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final UserServiceImpl userServiceImpl;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CorsProperties corsProperties;
    private final CustomBasicAuthenticationEntryPoint entryPoint;

    public SecurityConfig(UserServiceImpl userServiceImpl,
                          @Lazy BCryptPasswordEncoder passwordEncoder,
                          CorsProperties corsProperties,
                          CustomBasicAuthenticationEntryPoint entryPoint) {
        this.userServiceImpl = userServiceImpl;
        this.passwordEncoder = passwordEncoder;
        this.corsProperties = corsProperties;
        this.entryPoint = entryPoint;
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(8);

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .authorizeRequests().antMatchers("/api/v*/registration/**", "/api/oauth", "/api/login")
                .permitAll().anyRequest()
                .authenticated().and()
                .formLogin()
                .and()
                .httpBasic().authenticationEntryPoint(entryPoint)
                .and()
                .oauth2Login().successHandler(new CustomAuthenticationSuccessHandler())
                .and()
                .logout().logoutSuccessHandler((new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)));


    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userServiceImpl);
        return provider;
    }


    private class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
        @Override
        public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            User user = new User();
            user.setUsername(oAuth2User.getAttribute("name"));
            user.setGoogleId(oAuth2User.getAttribute("sub"));
            user.setEmail(oAuth2User.getAttribute("email"));
            user.setFirstName(oAuth2User.getAttribute("given_name"));
            user.setLastName(oAuth2User.getAttribute("family_name"));
            user.setRoles(Collections.singleton(Role.PERSON));


            userServiceImpl.addUserFromGoogle(user);
            httpServletResponse.sendRedirect(corsProperties.getUiUrl());
        }
    }
}
