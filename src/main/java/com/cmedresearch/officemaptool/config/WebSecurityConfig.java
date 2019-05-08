package com.cmedresearch.officemaptool.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  public void configure(WebSecurity web) {
    web.ignoring().antMatchers("/h2/**");
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.httpBasic().disable()
        .authorizeRequests()
        .antMatchers("/login/**").permitAll() // TODO: Ain't doing shit, should fix later
        .antMatchers(HttpMethod.GET, "**").permitAll()
        .antMatchers("**").authenticated();
  }
}
