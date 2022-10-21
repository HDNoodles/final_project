package com.example.applicationservice.config;

import com.example.applicationservice.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private JwtFilter jwtFilter;

    @Autowired
    public void setJwtFilter(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/application-service/*").hasAuthority("hr")
                .antMatchers("/application-service/applicationWorkFlow/update").hasAuthority("hr")
                .antMatchers("/application-service/applicationWorkFlow").hasAuthority("update")
                .antMatchers("/application-service/applicationWorkFlow/*").hasAuthority("hr")
                .antMatchers("/application-service/applicationWorkFlow/employeeId/*").hasAuthority("read")
                .anyRequest()
                .authenticated();
    }



}
