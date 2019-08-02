package com.largeScreen.api.controller;
import com.alibaba.fastjson.JSONObject;
import com.largeScreen.api.util.GlobalUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import com.largeScreen.api.service.ICommonService;

@RestController
@RequestMapping(value = "/api/common", produces = { "application/json;charset=UTF-8" })
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

    @GetMapping("/region/getRegionByCoord")
    public String getRegionByCoord(@RequestParam(name="lng")Double lng, @RequestParam(name="lat")Double lat) {
        Map map = commonService.getRegionByCoord(lng, lat);
        return GlobalUtil.PackResponse(map);
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

    @GetMapping("/spot/getJctbInfo")
    public String getJctbInfo(@RequestParam(name="layerId")String layerId, @RequestParam(name="tbbh")String tbbh, @RequestParam(name="xzqdm")String xzqdm) {
        Map map = commonService.getJctbInfo(layerId, tbbh, xzqdm);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/spot/getVisibleJctb")
    public String getVisibleJctb(@RequestParam(name="minx")Double minx, @RequestParam(name="miny")Double miny, @RequestParam(name="maxx")Double maxx, @RequestParam(name="maxy")Double maxy) {
        List<Map> maps = commonService.getVisibleJctb(minx, miny, maxx, maxy);
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/spot/getJctbAffix")
    public String getJctbAffix(@RequestParam(name="layerId")String layerId, @RequestParam(name="jctbId")String jctbId) {
        List<Map> maps = commonService.getJctbAffix(layerId, jctbId);
        return GlobalUtil.PackResponse(maps);
    }

    @PostMapping("/spot/addJctbAffix")
    public void addJctbAffix(@RequestParam(name="record")String record) throws Exception {
        commonService.addJctbAffix((Map) JSONObject.parse(record));
    }

    @PostMapping("/spot/editJctbInfo")
    public void editJctbInfo(@RequestParam(name="record")String record) throws Exception {
        commonService.editJctbInfo((Map) JSONObject.parse(record));
    }
}
