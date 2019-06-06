package com.largeScreen.api.controller;

import com.largeScreen.api.util.GlobalUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/login")
public class AuthController {

    @GetMapping("/success")
    public String success(HttpServletRequest request, HttpServletResponse response) {
        return GlobalUtil.PackResponse(response);
    }

    @GetMapping("/failure")
    public String failure(HttpServletRequest request, HttpServletResponse response) {
        return GlobalUtil.PackResponse(response);
    }
}
