package com.learn.java.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class InternalRequestFilter extends OncePerRequestFilter {
	@Value("${internal.api.token}")
	private String propToken;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String token = request.getHeader("Internal-Token");
		
		// Только для внутренних эндпоинтов
		if (uri.startsWith("/internal/")) {
			if (!propToken.equals(token)) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied: Invalid token");
				return; // не передаём в контроллер
			}
		}
		
		filterChain.doFilter(request, response); // идём дальше (к контроллеру)
	}
}
