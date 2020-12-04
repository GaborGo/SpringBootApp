package com.springboot.contapp.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
           http
                   .csrf().disable()
                   .authorizeRequests()
                   .mvcMatchers("/login").permitAll()
                   .mvcMatchers("/users/**").hasRole("ADMIN")
                   .mvcMatchers("/invoices/**").authenticated()
                   .and().formLogin().loginPage("/login")
                   .defaultSuccessUrl("/payments");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication().withUser("gabor").password(encoder.encode("pass")).roles("ADMIN");
    }

}
