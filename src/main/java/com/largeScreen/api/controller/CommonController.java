package com.largeScreen.api.controller;
import com.alibaba.fastjson.JSONObject;
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
    public String getRegionByLevel(@RequestParam(name="level")Short level) {
        List<Map> maps = commonService.getRegionByLevel(level);
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/region/getRegionByParent")
    public String getRegionByParent(@RequestParam(name="xzqdm")String xzqdm) {
        List<Map> maps = commonService.getRegionByParent(xzqdm);
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/region/getRegionByBounds")
    public String getRegionByBounds(@RequestParam(name="level")Short level, @RequestParam(name="wkt")String wkt) {
        List<Map> maps = commonService.getRegionByBounds(level, wkt);
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/region/getRegionByXzqdm")
    public String getRegionByXzqdm(@RequestParam(name="xzqdm")String xzqdm) {
        Map map = commonService.getRegionByXzqdm(xzqdm);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/region/getRegionWktByXzqdm")
    public String getRegionWktByXzqdm(@RequestParam(name="xzqdm")String xzqdm) {
        Map map = commonService.getRegionWktByXzqdm(xzqdm);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/region/getRegionTagByXzqdm")
    public String getRegionTagByXzqdm(@RequestParam(name="xzqdm")String xzqdm) {
        Map map = commonService.getRegionTagByXzqdm(xzqdm);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/region/getRegionGroups")
    public String getRegionGroups() {
        List<Map> maps = commonService.getRegionGroups();
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/config/getStorageAddress")
    public String getStorageAddress(@RequestParam(name="id")String id) {
        Map map = commonService.getStorageAddress(id);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/config/getStorageAddress")
    public String getJctbInfo(@RequestParam(name="layerId")String layerId, @RequestParam(name="jctbId")String jctbId) {
        Map map = commonService.getJctbInfo(layerId, jctbId);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/config/getStorageAddress")
    public String getVisibleJctb(@RequestParam(name="minx")Double minx, @RequestParam(name="miny")Double miny, @RequestParam(name="maxx")Double maxx, @RequestParam(name="maxy")Double maxy) {
        List<Map> maps = commonService.getVisibleJctb(minx, miny, maxx, maxy);
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/config/getStorageAddress")
    public String getJctbAffix(String layerId, String jctbId) {
        List<Map> maps = commonService.getJctbAffix(layerId, jctbId);
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/config/getStorageAddress")
    public void editJctbInfo(@RequestParam(name="record")String record) {
        commonService.editJctbInfo((Map) JSONObject.parse(record));
    }
}
