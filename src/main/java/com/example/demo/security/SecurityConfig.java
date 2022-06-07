package com.example.demo.security;

import com.example.demo.filters.CustomAuthenticationFilter;
import com.example.demo.filters.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private final UserDetailsService userDetailsService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.sessionManagement().sessionCreationPolicy(STATELESS);
//    http.authorizeRequests().anyRequest().permitAll();
    http.authorizeRequests().antMatchers( "/check/**").hasAnyAuthority("ADMIN");
    http.authorizeRequests().antMatchers( "/action/**").hasAnyAuthority("ADMIN");
    http.authorizeRequests().antMatchers( "/admins/**").hasAnyAuthority("ADMIN");
    http.authorizeRequests().anyRequest().permitAll();
    http.cors();
    http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
    http.addFilterBefore(
        new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
