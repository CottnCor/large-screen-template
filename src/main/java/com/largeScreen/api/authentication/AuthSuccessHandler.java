package com.largeScreen.api.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.largeScreen.api.base.Result;
import com.largeScreen.api.base.ResultGenerator;
import com.largeScreen.api.entity.JwtUser;
import com.largeScreen.api.entity.LoginUser;
import com.largeScreen.api.entity.User;
import com.largeScreen.api.service.IUserRepository;
import com.largeScreen.api.util.EntityUtil;
import com.largeScreen.api.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component("AuthSuccessHandler")
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private ThreadLocal<Integer> rememberMe = new ThreadLocal<>();

    @Autowired
    private IUserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Map parameterMap = EntityUtil.easyMap(request.getParameterMap());

        LoginUser loginUser = EntityUtil.mapToEntity(parameterMap, LoginUser.class);

        if (loginUser.getUsername() == null) {
            loginUser = new ObjectMapper().readValue(request.getInputStream(), LoginUser.class);
        }

        rememberMe.set(loginUser.getRememberMe());
        boolean isRemember = rememberMe.get() == 1;

        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();

        String token = JwtTokenUtil.createToken(jwtUser.getUsername(), isRemember).replace(JwtTokenUtil.TOKEN_PREFIX, "");

        User user = userRepository.findByUsername(jwtUser.getUsername());

        Result result = ResultGenerator.genSuccessResult(new HashMap(2) {{
            put("token", token);
            put("userid", user.getId());
        }});

        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("token", JwtTokenUtil.TOKEN_PREFIX + token);
        response.getWriter().write(new ObjectMapper().writeValueAsString(result));
    }
}