package com.largeScreen.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.largeScreen.api.util.GlobalUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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

    @GetMapping("/config/getOssConfig")
    public String getOssConfig() {
        Map map = commonService.getOssConfig();
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/config/getConfigDic")
    public String getEnumeratorDic(@RequestParam(name="layerId")String layerId, @RequestParam(name="type")String type, @RequestParam(name="pid")Integer pid, @RequestParam(name="subtype")Integer subtype) throws Exception {
        List<Map> map = commonService.getConfigDic(layerId, type, pid, subtype);
        return GlobalUtil.PackResponse(map);
    }


    @GetMapping("/config/getEnumeratorDic")
    public String getEnumeratorDic(@RequestParam(name="key")String key) throws Exception {
        List<Map> map = commonService.getEnumeratorDic(key);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/spot/getSupervisedInfoDic")
    public String getSupervisedInfoDic(@RequestParam(name="level")Integer level, @RequestParam(name="layerId")String layerId) throws Exception {
        List<Map> map = commonService.getSupervisedInfoDic(level, layerId);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/spot/getJzInfoDic")
    public String getJzInfoDic(@RequestParam(name="layerId")String layerId) throws Exception {
        List<Map> map = commonService.getJzInfoDic(layerId);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/spot/getJctbInfoDic")
    public String getJctbInfoDic(@RequestParam(name="layerId")String layerId) throws Exception {
        List<Map> map = commonService.getJctbInfoDic(layerId);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/spot/getJctbInfo")
    public String getJctbInfo(@RequestParam(name="filter")String filter) throws UnsupportedEncodingException {
        filter = URLDecoder.decode(filter, "utf-8");
        String layerId = JSON.parseObject(filter).getString("layerId");
        String tbbh = JSON.parseObject(filter).getString("tbbh");
        String xzqdm = JSON.parseObject(filter).getString("xzqdm");
        List<String> fields = JSON.parseObject(filter).getJSONArray("fields").toJavaList(String.class);
        Map map = commonService.getJctbInfo(layerId, tbbh, xzqdm, fields);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/spot/getVisibleJctb")
    public String getVisibleJctb(@RequestParam(name="minx")Double minx, @RequestParam(name="miny")Double miny, @RequestParam(name="maxx")Double maxx, @RequestParam(name="maxy")Double maxy) {
        List<Map> maps = commonService.getVisibleJctb(minx, miny, maxx, maxy);
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/spot/getJctbAffix")
    public String getJctbAffix(@RequestParam(name="layerId")String layerId, @RequestParam(name="tbbh")String tbbh, @RequestParam(name="xzqdm")String xzqdm) {
        List<Map> maps = commonService.getJctbAffix(layerId, tbbh, xzqdm);
        return GlobalUtil.PackResponse(maps);
    }

    @PostMapping("/spot/addJctbAffix")
    public String addJctbAffix(@RequestParam(name="record")String record) throws Exception {
        record = URLDecoder.decode(record, "utf-8");
        String layerId = JSON.parseObject(record).getString("layerId");
        String tbbh = JSON.parseObject(record).getString("tbbh");
        String xzqdm = JSON.parseObject(record).getString("xzqdm");
        List<Map> params = JSON.parseObject(record).getJSONArray("params").toJavaList(Map.class);
        Boolean flag = commonService.addJctbAffix(layerId, tbbh, xzqdm, params);
        return GlobalUtil.PackResponse(flag);
    }

    @PostMapping("/spot/editJctbInfo")
    public String editJctbInfo(@RequestParam(name="record")String record) throws Exception {
        record = URLDecoder.decode(record, "utf-8");
        String layerId = JSON.parseObject(record).getString("layerId");
        String tbbh = JSON.parseObject(record).getString("tbbh");
        String xzqdm = JSON.parseObject(record).getString("xzqdm");
        List<Map> params = JSON.parseObject(record).getJSONArray("params").toJavaList(Map.class);
        Boolean flag = commonService.editJctbInfo(layerId, tbbh, xzqdm, params);
        return GlobalUtil.PackResponse(flag);
    }

    @GetMapping("/config/invokeMethod")
    public String invokeMethod(@RequestParam(name="method")String method) {
        Map maps = commonService.invokeMethod(method);
        return GlobalUtil.PackResponse(maps);
    }
}
