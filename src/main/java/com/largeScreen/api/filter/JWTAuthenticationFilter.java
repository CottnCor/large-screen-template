package com.largeScreen.api.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.largeScreen.api.base.Result;
import com.largeScreen.api.base.ResultGenerator;
import com.largeScreen.api.entity.JwtUser;
import com.largeScreen.api.entity.LoginUser;
import com.largeScreen.api.util.EntityUtil;
import com.largeScreen.api.util.GlobalUtil;
import com.largeScreen.api.util.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private ThreadLocal<Integer> rememberMe = new ThreadLocal<>();

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws org.springframework.security.core.AuthenticationException {
        try {
            Map parameterMap = EntityUtil.easyMap(request.getParameterMap());
            LoginUser loginUser = EntityUtil.mapToEntity(parameterMap, LoginUser.class);
            if(loginUser.getUsername() == null){
                loginUser = new ObjectMapper().readValue(request.getInputStream(), LoginUser.class);
            }
            rememberMe.set(loginUser.getRememberMe());
            Authentication feedback = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword(), new ArrayList<>()));
            return  feedback;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        JwtUser jwtUser = (JwtUser) authResult.getPrincipal();
        boolean isRemember = rememberMe.get() == 1;
        String token = JwtTokenUtil.createToken(jwtUser.getUsername(), isRemember);
        response.setHeader("token", JwtTokenUtil.TOKEN_PREFIX + token);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        Result result = ResultGenerator.genSuccessResult(token.replace(JwtTokenUtil.TOKEN_PREFIX, ""));
        response.getWriter().write(new ObjectMapper().writeValueAsString(result));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        Result result = ResultGenerator.genFailResult("authentication failed, reason: " + failed.getMessage());
        response.getWriter().write(new ObjectMapper().writeValueAsString(result));
    }
}
