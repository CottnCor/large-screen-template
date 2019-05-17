package com.largeScreen.api.controller;

import com.largeScreen.api.service.IStorageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/storage")
public class StorageControllor {

    @Resource
    private IStorageService storageService;
}
