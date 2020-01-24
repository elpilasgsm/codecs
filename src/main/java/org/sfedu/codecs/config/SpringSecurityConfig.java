package org.sfedu.codecs.config;

import org.sfedu.codecs.constants.UserRoles;
import org.sfedu.codecs.repository.db.UserRepository;
import org.sfedu.codecs.security.CodecsUserDetailsService;
import org.sfedu.codecs.security.SHA256PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService())
                .passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/api/**").hasAnyAuthority(UserRoles.ADMIN.getAuthority(), UserRoles.MODERATOR.getAuthority())
                .antMatchers("/public/**").hasAnyAuthority(UserRoles.ADMIN.getAuthority(), UserRoles.USER.getAuthority(), UserRoles.MODERATOR.getAuthority())
                .and().httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new SHA256PasswordEncoder();
    }

    @Bean
    public CodecsUserDetailsService customUserDetailsService() {
        return new CodecsUserDetailsService(userRepository);
    }


}