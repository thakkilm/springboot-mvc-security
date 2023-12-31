package com.spring.mahesh.demoSecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return  new JdbcUserDetailsManager(dataSource);
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//        UserDetails mahesh= User.builder()
//                .username("mahesh")
//                .password("{noop}dummy")
//                .roles("EMPLOYEE")
//                .build();
//        UserDetails lokesh= User.builder()
//                .username("lokesh")
//                .password("{noop}dummy")
//                .roles("EMPLOYEE","MANAGER")
//                .build();
//        UserDetails lavanya= User.builder()
//                .username("lavanya")
//                .password("{noop}dummy")
//                .roles("EMPLOYEE","MANAGER","ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(mahesh,lokesh,lavanya);
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer->
                configurer.requestMatchers("/").hasRole("EMPLOYEE").
                        requestMatchers("/leaders/**").hasRole("MANAGER").
                       requestMatchers("/systems/**").hasRole("ADMIN").

                        anyRequest().authenticated()).
                formLogin(form->form
                        .loginPage("/showMyLoginPage") //The parameter will navigate the page tpo the corresponding method on controller
                        .loginProcessingUrl("/authenticateTheUser")  //This parameter is spring inbuilt should be same
                        .permitAll())
                .logout(logout-> logout.permitAll()).exceptionHandling(configurer->configurer.accessDeniedPage("/access-denied"));
        return http.build();
    }
}
