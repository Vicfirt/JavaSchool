package com.javaschool.onlineshop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan("com.javaschool.onlineshop")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   private final MyUserDetailsService userDetailsService;

    public SecurityConfig(MyUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean(name = "passwordEncoder")
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/", "/home").permitAll()
                .antMatchers("/catalog", "/cart/**").permitAll().antMatchers("/product/employee/**")
                .hasAuthority("EMPLOYEE").antMatchers("/login", "/signup").permitAll()
                .antMatchers("/profile","/orders/order/new", "/orders/all", "orders/receipt").hasAnyAuthority("CUSTOMER")
                .antMatchers("/orders/status","/orders","orders/deletion", "orders/receipt").hasAnyAuthority("EMPLOYEE")
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/").loginProcessingUrl("/login").failureUrl("/login/error")
                .usernameParameter("email").passwordParameter("password").and().logout()
                .logoutUrl("/logout").logoutSuccessUrl("/")
                .and().exceptionHandling().accessDeniedPage("/denied");
    }
}
