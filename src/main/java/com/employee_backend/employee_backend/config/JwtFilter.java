package com.employee_backend.employee_backend.config;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.employee_backend.employee_backend.repository.UserRepository;
import com.employee_backend.employee_backend.service.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//			throws ServletException, IOException {
//		String authHeader = request.getHeader("Authorization");
//
//		if ("OPTIONS".equals(request.getMethod()) || request.getRequestURL().toString().contains("/swagger")
//				|| request.getRequestURL().toString().contains("/api-docs")
//				|| request.getRequestURL().toString().contains("/actuator/health")
//				|| request.getRequestURL().toString().contains("/auth/login")) {
//			response.setStatus(HttpServletResponse.SC_OK);
//			chain.doFilter(request, response);
//			return;
//		}
//
//		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//			chain.doFilter(request, response);
//			return;
//		}
//
//		if (authHeader != null && authHeader.startsWith("Bearer ")) {
//			String token = authHeader.substring(7);
//			if (jwtUtil.validateToken(token)) {
//				String username = jwtUtil.extractUsername(token);
//				UserDetails userDetails = (UserDetails) userRepo.findByUsername(username).orElse(null);
//				if (userDetails != null) {
//					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
//							null,
//							Collections.singleton(new SimpleGrantedAuthority("ROLE_" + jwtUtil.extractRole(token))));
//					SecurityContextHolder.getContext().setAuthentication(authToken);
//				}
//			}
//		}
//		chain.doFilter(request, response);
//	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
	                                HttpServletResponse response,
	                                FilterChain chain)
	        throws ServletException, IOException {

	    String header = request.getHeader("Authorization");
	    if (header != null && header.startsWith("Bearer ")) {
	        String token = header.substring(7);
	        if (jwtUtil.validateToken(token)) {
	            String username = jwtUtil.extractUsername(token);

	            // ✨ load via UserDetailsService, NOT by casting your entity
	            UserDetails userDetails =
	                    customUserDetailsService.loadUserByUsername(username);

	            UsernamePasswordAuthenticationToken auth =
	                new UsernamePasswordAuthenticationToken(
	                        userDetails, null, userDetails.getAuthorities());

	            SecurityContextHolder.getContext().setAuthentication(auth);
	        }
	    }
	    chain.doFilter(request, response);
	}

}
