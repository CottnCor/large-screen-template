package com.largeScreen.api.controller;

import com.largeScreen.api.service.IFruitService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/api/fruit", produces = { "application/json;charset=UTF-8" })
public class FruitController {

    @Resource
    private IFruitService fruitService;
}
