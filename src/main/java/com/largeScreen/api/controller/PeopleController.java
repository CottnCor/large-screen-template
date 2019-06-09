package com.largeScreen.api.controller;

import com.largeScreen.api.service.IPeopleService;
import com.largeScreen.api.util.GlobalUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/people")
public class PeopleController {

    @Resource
    private IPeopleService peopleService;

    @GetMapping("/getPeopleOverview")
    public String getUserOverview(@RequestParam(name="type",required=true)String type) {
        Map map = peopleService.getPeopleOverview(type);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/getLevelCounts")
    public String getLevelCounts(@RequestParam(name="type",required=true)String type) {
        List<Map> maps = peopleService.getLevelCounts(type);
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/getPeopleCounts")
    public String getPeopleCounts(@RequestParam(name="type",required=true)String type, @RequestParam(name="xzqdm",required=false)String xzqdm) {
        List<Map> maps = peopleService.getPeopleCounts(type, xzqdm);
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/getPeopleCoords")
    public String getPeopleCoords(@RequestParam(name="level",required=true)Short level, @RequestParam(name="bounds",required=false)String bounds) {
        List<Map> maps = peopleService.getPeopleCoords(level, bounds);
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/getYxInfo")
    public String getYxAccount(@RequestParam(name="userid",required=true)Long userid) {
        Map map = peopleService.getYxInfo(userid);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/getUserInfo")
    public String getUserInfo(@RequestParam(name="userid",required=true)Long userid) {
        Map map = peopleService.getUserInfo(userid);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/getUserState")
    public String getUserState(@RequestParam(name="userid",required=true)Long userid) {
        Map map = peopleService.getUserState(userid);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/getUserCoords")
    public String getUserLocation(@RequestParam(name="userid",required=true)Long userid) {
        Map map = peopleService.getUserCoords(userid);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/getUserTree")
    public String getUserTree() {
        List<Map> maps = peopleService.getUserTree();
        return GlobalUtil.PackResponse(maps);
    }

    @PostMapping("/addMessage")
    public String addMessage(@RequestParam(name="userid",required=true)Long userid, @RequestParam(name="msg",required=true)String msg) {
        Boolean status = peopleService.sendMsg(userid, msg);
        return GlobalUtil.PackResponse(status);
    }
}
