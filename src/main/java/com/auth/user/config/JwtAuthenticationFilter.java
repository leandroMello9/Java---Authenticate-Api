package com.auth.user.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth.user.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private final JwtService jwtService;


    //Intercept the request to validate the JWT token
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException{
        
        String headerInRequest = request.getHeader("Authorization");

        if (headerInRequest == null || !headerInRequest.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String getTokenHeaderRequest = headerInRequest.substring(7);
        
        if(jwtService.isValidToken(getTokenHeaderRequest)) {
            Authentication userAuthenticated = jwtService.getAuthentication(getTokenHeaderRequest);
            SecurityContextHolder.getContext().setAuthentication(userAuthenticated);
        }
        filterChain.doFilter(request, response);
    }
  
}