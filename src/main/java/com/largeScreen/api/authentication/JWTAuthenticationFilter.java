package com.largeScreen.api.authentication;

import com.largeScreen.api.util.JwtTokenUtil;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String tokenHeader = request.getHeader(JwtTokenUtil.TOKEN_HEADER);

        if (tokenHeader != null) {
            JwtTokenUtil.refreshToken(tokenHeader);
        }

        filterChain.doFilter(request, response);
    }
}
