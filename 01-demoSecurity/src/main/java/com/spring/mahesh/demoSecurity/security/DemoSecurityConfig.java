package com.spring.mahesh.demoSecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails mahesh= User.builder()
                .username("mahesh")
                .password("{noop}dummy")
                .roles("EMPLOYEE")
                .build();
        UserDetails lokesh= User.builder()
                .username("lokesh")
                .password("{noop}dummy")
                .roles("EMPLOYEE","MANAGER")
                .build();
        UserDetails lavanya= User.builder()
                .username("lavanya")
                .password("{noop}dummy")
                .roles("EMPLOYEE","MANAGER","ADMIN")
                .build();
        return new InMemoryUserDetailsManager(mahesh,lokesh,lavanya);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer->
                configurer.anyRequest().authenticated()).
                formLogin(form->form
                        .loginPage("/showMyLoginPage") //The parameter will navigate the page tpo the corresponding method on controller
                        .loginProcessingUrl("/authenticateTheUser")  //This parameter is spring inbuilt should be same
                        .permitAll());
        return http.build();
    }
}
