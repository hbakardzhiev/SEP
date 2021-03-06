package com.example.demo.filters;

import com.example.demo.Util;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class CustomAuthorizationFilter extends OncePerRequestFilter {

  /**
   * Method that tries to retrieve the Authorization header value and checks if the token in it has
   * the correct permissions to access the desired endpoint.
   */
  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String authorizationHeader = request.getHeader(AUTHORIZATION);
    try {
      if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
        final var token = authorizationHeader.substring("Bearer ".length());
        final var decodedJWT = Util.getDecodedJWT(token);
        final var username = decodedJWT.getSubject();
        final var role = decodedJWT.getClaim("roles").asString();
        final var authorities = List.of(new SimpleGrantedAuthority(role));

        final var authenticationToken =
            new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
    } catch (Exception e) {
      response.setHeader("error", e.getMessage());
      response.setStatus(FORBIDDEN.value());

      response.setContentType(APPLICATION_JSON_VALUE);
      new ObjectMapper().writeValue(response.getOutputStream(), e.getMessage());
    }
    filterChain.doFilter(request, response);
  }
}
