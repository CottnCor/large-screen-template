package com.largeScreen.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import com.largeScreen.api.service.ICommonService;

@RestController
@RequestMapping("/api/common")
public class CommonController {

    @Resource
    private ICommonService commonService;

    @GetMapping(value = "/region/getRegionByLevel/{level}")
    public String getRegionByLevel(@PathVariable("level") Integer level) {
        List<Map> maps = commonService.getRegionByLevel(level);
        return JSON.toJSONString(maps, SerializerFeature.WriteMapNullValue);
    }
}
