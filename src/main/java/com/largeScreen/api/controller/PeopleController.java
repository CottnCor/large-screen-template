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
    public String getUserOverview(@RequestParam(name="type")String type) {
        Map map = peopleService.getPeopleOverview(type);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/getLevelCounts")
    public String getLevelCounts(@RequestParam(name="type")String type) {
        List<Map> maps = peopleService.getLevelCounts(type);
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/getPeopleCounts")
    public String getPeopleCounts(@RequestParam(name="type")String type, @RequestParam(name="xzqdm",required=false)String xzqdm) {
        List<Map> maps = peopleService.getPeopleCounts(type, xzqdm);
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/getPeopleCoords")
    public String getPeopleCoords(@RequestParam(name="level")Short level, @RequestParam(name="bounds",required=false)String bounds) {
        List<Map> maps = peopleService.getPeopleCoords(level, bounds);
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/getYxInfo")
    public String getYxAccount(@RequestParam(name="userid")Long userid) {
        Map map = peopleService.getYxInfo(userid);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/getUserInfo")
    public String getUserInfo(@RequestParam(name="userid")Long userid) {
        Map map = peopleService.getUserInfo(userid);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/getUserState")
    public String getUserState(@RequestParam(name="userid")Long userid) {
        Map map = peopleService.getUserState(userid);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/getUserCoords")
    public String getUserLocation(@RequestParam(name="userid")Long userid) {
        Map map = peopleService.getUserCoords(userid);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/getUserTree")
    public String getUserTree() {
        List<Map> maps = peopleService.getUserTree();
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/getPersonTime")
    public String getPersonTime(@RequestParam(name="startTime")String startTime, @RequestParam(name="endTime")String endTime) {
        Map map = peopleService.getPersonTime(startTime, endTime);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/getPersonTimeCounts")
    public String getPersonTimeCounts(@RequestParam(name="startTime")String startTime, @RequestParam(name="endTime")String endTime, @RequestParam(name="xzqdm",required=false)String xzqdm) {
        List<Map> maps = peopleService.getPersonTimeCounts(startTime, endTime, xzqdm);
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/getActivePerson")
    public String getActivePerson(@RequestParam(name="startTime")String startTime, @RequestParam(name="endTime")String endTime, @RequestParam(name="limit")Short limit, @RequestParam(name="page")Short page) {
        List<Map> maps = peopleService.getActivePerson(startTime, endTime, limit, page);
        return GlobalUtil.PackResponse(maps);
    }

    @PostMapping("/addMessage")
    public String addMessage(@RequestParam(name="userid")Long userid, @RequestParam(name="msg")String msg) {
        Boolean status = peopleService.sendMsg(userid, msg);
        return GlobalUtil.PackResponse(status);
    }
}
