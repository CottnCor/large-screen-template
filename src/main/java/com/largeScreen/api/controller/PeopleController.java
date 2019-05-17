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

    @GetMapping("/getYxAccount")
    public String getYxAccount(@RequestParam(name="userid",required=true)String userid) {
        Map map = peopleService.getYxAccount(userid);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/getUserInfo")
    public String getUserInfo(@RequestParam(name="userid",required=true)String userid) {
        Map map = peopleService.getUserInfo(userid);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/getUserState")
    public String getUserState(@RequestParam(name="userid",required=true)String userid) {
        Map map = peopleService.getUserState(userid);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/getUserLocation")
    public String getUserLocation(@RequestParam(name="userid",required=true)String userid) {
        Map map = peopleService.getUserLocation(userid);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/getUserTree")
    public String getUserTree() {
        List<Map> maps = peopleService.getUserTree();
        return GlobalUtil.PackResponse(maps);
    }

    @PostMapping("/addMessage")
    public String addMessage(@RequestParam(name="userid",required=true)String userid) {
        Boolean status = peopleService.addMessage(userid);
        return GlobalUtil.PackResponse(status);
    }
}
