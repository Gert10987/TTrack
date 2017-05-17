package com.gert.security;

import com.gert.security.restApi.CustomBasicAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@EnableWebSecurity
public class SecurityConfig {

    @Configuration
    @Order(1)
    public static class RestApiSecurityConfig extends WebSecurityConfigurerAdapter {

        private String REALM="MY_TEST_REALM";

        @Qualifier("employerServiceRestApi")
        @Autowired
        private UserDetailsService employerDetailsServiceImpl;

        @Autowired
        public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(employerDetailsServiceImpl);
        }

        @Override protected void configure(HttpSecurity http) throws Exception {

            http
                    .antMatcher("/restApi/**")
                    .csrf()
                    .disable()
                    .authorizeRequests().antMatchers("/restApi/**").hasRole("USER").and()
                    .httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint()).and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    .exceptionHandling().authenticationEntryPoint(getBasicAuthEntryPoint());
        }

        @Bean
        public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
            return new CustomBasicAuthenticationEntryPoint();
        }
    }

    @Configuration
    @Order(2)
    public static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Autowired
        @Qualifier("customUserDetailsService")
        UserDetailsService userDetailsService;

        @Autowired
        PersistentTokenRepository tokenRepositoryUser;

        @Autowired
        public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService);
            auth.authenticationProvider(authenticationProvider());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.authorizeRequests().antMatchers("/list")
                    .access("hasRole('ADMIN') or hasRole('DBA')")
                    .antMatchers("/newuser/**", "/delete-user-*", "/manage-employer-*").access("hasRole('ADMIN')").antMatchers("/edit-user-*")
                    .access("hasRole('ADMIN') or hasRole('DBA')").and().formLogin().loginPage("/login")
                    .loginProcessingUrl("/login").usernameParameter("ssoId").passwordParameter("password").and()
                    .rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepositoryUser)
                    .tokenValiditySeconds(86400).and().csrf().and().exceptionHandling().accessDeniedPage("/Access_Denied");

        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public DaoAuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
            authenticationProvider.setUserDetailsService(userDetailsService);
            authenticationProvider.setPasswordEncoder(passwordEncoder());
            return authenticationProvider;
        }

        @Bean
        public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
            PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices(
                    "remember-me", userDetailsService, tokenRepositoryUser);
            return tokenBasedservice;
        }

        @Bean
        public AuthenticationTrustResolver getAuthenticationTrustResolver() {
            return new AuthenticationTrustResolverImpl();
        }
    }
}