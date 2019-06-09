package com.largeScreen.api.controller;

import com.largeScreen.api.util.GlobalUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import com.largeScreen.api.service.ICommonService;

@RestController
@RequestMapping("/api/common")
public class CommonController {

    @Resource
    private ICommonService commonService;

    @GetMapping("/region/getRegionByLevel")
    public String getRegionByLevel(@RequestParam(name="level",required=true)Short level) {
        List<Map> maps = commonService.getRegionByLevel(level);
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/region/getRegionByParent")
    public String getRegionByParent(@RequestParam(name="xzqdm",required=true)String xzqdm) {
        List<Map> maps = commonService.getRegionByParent(xzqdm);
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/region/getRegionByBounds")
    public String getRegionByBounds(@RequestParam(name="level",required=true)Short level, @RequestParam(name="wkt",required=true)String wkt) {
        List<Map> maps = commonService.getRegionByBounds(level, wkt);
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/region/getRegionByXzqdm")
    public String getRegionByXzqdm(@RequestParam(name="xzqdm",required=true)String xzqdm) {
        Map map = commonService.getRegionByXzqdm(xzqdm);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/region/getRegionWktByXzqdm")
    public String getRegionWktByXzqdm(@RequestParam(name="xzqdm",required=true)String xzqdm) {
        Map map = commonService.getRegionWktByXzqdm(xzqdm);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/region/getRegionTagByXzqdm")
    public String getRegionTagByXzqdm(@RequestParam(name="xzqdm",required=true)String xzqdm) {
        Map map = commonService.getRegionTagByXzqdm(xzqdm);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/region/getRegionGroups")
    public String getRegionGroups() {
        List<Map> maps = commonService.getRegionGroups();
        return GlobalUtil.PackResponse(maps);
    }
}
