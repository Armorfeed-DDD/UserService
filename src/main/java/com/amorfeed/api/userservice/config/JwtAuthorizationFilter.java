package com.amorfeed.api.userservice.config;

import com.amorfeed.api.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private static final Logger logger= LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    @Autowired
    private JwtHandler handler;

    @Autowired
    private UserService userService;

    private String parseTokenFrom(HttpServletRequest request){
        String authorizationParameter = request.getHeader("Authorization");

        if(StringUtils.hasText(authorizationParameter) && authorizationParameter.startsWith("Bearer")){
            return new LinkedList<>(
                    Arrays.asList(authorizationParameter.split(" "))).getLast();
        }

        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException{
        try{
            String token=parseTokenFrom(request);
            if(token==null || !handler.validateToken(token)){
                filterChain.doFilter(request,response);
                return;
            }
            logger.info("Token: {}", token);
            String username=handler.getUsernameFrom(token);
            UserDetails principal=userService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken=
                    new UsernamePasswordAuthenticationToken(principal, null,
                            principal.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (Exception e){
            logger.error("\"User Authentication cannot be set: {}", e.getMessage());
        }
        filterChain.doFilter(request,response);
    }
}
