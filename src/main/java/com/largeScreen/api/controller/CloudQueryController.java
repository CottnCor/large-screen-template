package com.largeScreen.api.controller;

import com.largeScreen.api.service.ICloudQueryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/cloudquery")
public class CloudQueryController {

    @Resource
    private ICloudQueryService queryService;
}
