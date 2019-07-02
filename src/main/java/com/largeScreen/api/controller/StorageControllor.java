package com.largeScreen.api.controller;

import com.largeScreen.api.service.IStorageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/api/storage", produces = { "application/json;charset=UTF-8" })
public class StorageControllor {

    @Resource
    private IStorageService storageService;
}
