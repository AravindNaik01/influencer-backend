package com.example.demo;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	        throws IOException, ServletException {

	    HttpServletRequest req = (HttpServletRequest) request;

	    String path = req.getRequestURI();

	    // ✅ Skip auth for login & register
	    if (path.equals("/login") || path.equals("/register")) {
	        chain.doFilter(request, response);
	        return;
	    }

	    String header = req.getHeader("Authorization");

	    if (header == null || !header.startsWith("Bearer ")) {
	        ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing token");
	        return;
	    }

	    String token = header.substring(7);

	    try {
	        JwtUtil.extractUsername(token);
	    } catch (Exception e) {
	        ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
	        return;
	    }

	    chain.doFilter(request, response);
	}
}