//package com.crud.rest313.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.csrf.CsrfFilter;
//import org.springframework.web.filter.CharacterEncodingFilter;
//
//@Configuration
//public class SecurityConfigTestAuthentication extends WebSecurityConfigurerAdapter {
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    // Create 2 users for demo
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication()
//                .withUser("user@a").password("{noop}password").roles("USER")
//                .and()
//                .withUser("admin@a").password("{noop}qqq").roles("USER", "ADMIN");
//
//    }
//
//    // Secure the endpoins with HTTP Basic authentication
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        var filter = new CharacterEncodingFilter();
//        filter.setEncoding("UTF-8");
//        filter.setForceEncoding(true);
//        http.addFilterBefore(filter, CsrfFilter.class);
//
//        http
//                //HTTP Basic authentication
//                .httpBasic()
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/user/**").hasRole("USER")
//                .antMatchers(HttpMethod.POST, "/admin/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT, "/admin/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PATCH, "/admin/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/admin/**").hasRole("ADMIN")
//                .and()
//                .csrf().disable()
//                .formLogin().disable();
//        http
//                .authorizeRequests()
//                .antMatchers("/registration", "/login", "/").permitAll()
//                .antMatchers("/user/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
//                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
//                .anyRequest()
//                .authenticated();
//    }
//}
