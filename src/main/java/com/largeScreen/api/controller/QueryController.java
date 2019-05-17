package com.largeScreen.api.controller;

import com.largeScreen.api.service.IQueryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/query")
public class QueryController {

    @Resource
    private IQueryService queryService;
}
