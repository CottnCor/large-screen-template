package com.largeScreen.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.largeScreen.api.base.Result;
import com.largeScreen.api.base.ResultGenerator;
import com.largeScreen.api.util.GlobalUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Result result = ResultGenerator.genSuccessResult("已退出登陆！");
        return GlobalUtil.PackResponse(result);
    }

    @GetMapping("/login/info")
    public String info(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Result result = ResultGenerator.genForbiddenResult("请先登陆！");
        return GlobalUtil.PackResponse(result);
    }
}
