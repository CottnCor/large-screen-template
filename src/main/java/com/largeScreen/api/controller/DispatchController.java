package com.largeScreen.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.largeScreen.api.service.IDispatchService;
import com.largeScreen.api.util.GlobalUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dispatch")
public class DispatchController {

    @Resource
    private IDispatchService dispatchService;

    @GetMapping("/getDispatchOverview")
    public String getDispatchOverview() {
        List<Map> maps = dispatchService.getDispatchOverview();
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/getDispatchMap")
    public String getDispatchMap(@RequestParam(name="limit",required=true)Integer limit,
                                 @RequestParam(name="page",required=true)Integer page,
                                 @RequestParam(name="start",required=true)String start,
                                 @RequestParam(name="end",required=true)String end,
                                 @RequestParam(name="state",required=true)Integer state) {
        List<Map> maps = dispatchService.getDispatchMap(limit, page, start, end, state);
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/getDispatchList")
    public String getDispatchList(@RequestParam(name="limit",required=true)Integer limit,
                                  @RequestParam(name="page",required=true)Integer page,
                                  @RequestParam(name="start",required=true)String start,
                                  @RequestParam(name="end",required=true)String end,
                                  @RequestParam(name="state",required=true)Integer state) {
        List<Map> maps = dispatchService.getDispatchList(limit, page, start, end, state);
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/getDispatchInfo")
    public String getDispatchInfo(@RequestParam(name="id",required=true)String id) {
        Map map = dispatchService.getDispatchInfo(id);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/getDispatchLocation")
    public String getDispatchLocation(@RequestParam(name="id",required=true)String id) {
        Map map = dispatchService.getDispatchLocation(id);
        return GlobalUtil.PackResponse(map);
    }

    @PostMapping("/addDispatchRecord")
    public String addDispatchRecord() {
        Boolean status = dispatchService.addDispatchRecord();
        return GlobalUtil.PackResponse(status);
    }

    @PostMapping("/setDispatchState")
    public String setDispatchState(@RequestParam(name="id",required=true)String id,
                                   @RequestParam(name="state",required=true)Integer state,
                                   @RequestParam(name="connState",required=true)Integer connState) {
        Boolean status = dispatchService.setDispatchState(id, state, connState);
        return GlobalUtil.PackResponse(status);
    }

    @PostMapping("/lockDispatch")
    public String lockDispatch(@RequestParam(name="id",required=true)String id) {
        Boolean status = dispatchService.lockDispatch(id);
        return GlobalUtil.PackResponse(status);
    }

    @PostMapping("/unlockDispatch")
    public String unlockDispatch(@RequestParam(name="id",required=true)String id) {
        Boolean status = dispatchService.unlockDispatch(id);
        return GlobalUtil.PackResponse(status);
    }

    @GetMapping("/statisticDispatch")
    public String statisticDispatch(@RequestParam(name="start",required=true)String start,
                                    @RequestParam(name="end",required=true)String end) {
        List<Map> maps = dispatchService.statisticDispatch(start, end);
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/getTimeSegments")
    public String getTimeSegments() {
        List<Map> maps = dispatchService.getTimeSegments();
        return GlobalUtil.PackResponse(maps);
    }

}
