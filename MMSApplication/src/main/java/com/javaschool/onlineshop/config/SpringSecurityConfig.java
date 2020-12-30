package com.javaschool.onlineshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@ComponentScan("com.javaschool.onlineshop")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    DataSource dataSource;

    public SpringSecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean(name = "passwordEncoder")
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select customer_email_address, customer_password, active " +
                        "from customer where customer_email_address=?")
                .authoritiesByUsernameQuery("select customer_email_address, customer_role from customer" +
                        " where customer_email_address = ? ");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/", "/home").permitAll()
                .antMatchers("/catalog", "/cart/**").permitAll().antMatchers("/product/employee/**")
                .hasAuthority("EMPLOYEE").antMatchers("/login", "/signup").permitAll()
                .antMatchers("/orders/customer/**").permitAll().and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/").loginProcessingUrl("/login").failureUrl("/login/error")
                .usernameParameter("email").passwordParameter("password").and().logout().permitAll()
                .and().exceptionHandling().accessDeniedPage("/access_denied");
    }
}
