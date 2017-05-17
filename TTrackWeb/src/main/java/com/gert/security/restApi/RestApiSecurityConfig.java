package com.gert.security.restApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Created by gert on 17.05.17.
 */
@Configuration
@EnableWebSecurity
@Order(1)
public class RestApiSecurityConfig extends WebSecurityConfigurerAdapter {

    private String tokenKey = "some token goes here";

    private static String REALM="MY_TEST_REALM";

    @Qualifier("employerServiceRestApi")
    @Autowired
    private UserDetailsService employerDetailsServiceImpl;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(employerDetailsServiceImpl);
    }

    @Override protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/restApi/**").hasRole("ADMIN")
                .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//We don't need sessions to be created.
    }

    @Bean
    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
        return new CustomBasicAuthenticationEntryPoint();
    }

    /**
     * Remember me config
     */
    @Bean
    public RememberMeAuthenticationFilter rememberMeAuthenticationFilter() throws Exception{
        return new RememberMeAuthenticationFilter(authenticationManager(), tokenBasedRememberMeService());
    }
    @Bean public CustomTokenBasedRememberMeService tokenBasedRememberMeService(){
        CustomTokenBasedRememberMeService service = new CustomTokenBasedRememberMeService(tokenKey, employerDetailsServiceImpl);
        service.setAlwaysRemember(true);
        service.setCookieName("at");
        return service;
    }
    @Bean
    RememberMeAuthenticationProvider rememberMeAuthenticationProvider(){
        return new RememberMeAuthenticationProvider(tokenKey);
    }
}
