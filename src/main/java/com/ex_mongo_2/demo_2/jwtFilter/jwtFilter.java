package com.ex_mongo_2.demo_2.jwtFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ex_mongo_2.demo_2.service.CustomeUserDetailServices;
import com.ex_mongo_2.demo_2.service.NewUserService;
import com.ex_mongo_2.demo_2.utils.Jwtutility;

import io.jsonwebtoken.io.IOException;


@Component
public class jwtFilter extends OncePerRequestFilter{

	@Autowired
	    private CustomeUserDetailServices userDetailsService;

	    @Autowired
	    private Jwtutility jwtUtil;
	    
	    @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
	        String authorizationHeader = request.getHeader("Authorization");
	        String username = null;
	        String jwt = null;
	        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	            jwt = authorizationHeader.substring(7);
	            username = jwtUtil.extractUsername(jwt);
	        }
	        if (username != null) {
	            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	            if (jwtUtil.validateToken(jwt)) {
	                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(auth);
	            }
	        }
	        
	        response.addHeader("admin","vimal");
	        try {
				chain.doFilter(request, response);
			} catch (java.io.IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
