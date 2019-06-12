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
    public String getDispatchMap(@RequestParam(name="limit")Integer limit,
                                 @RequestParam(name="page")Integer page,
                                 @RequestParam(name="start")String start,
                                 @RequestParam(name="end")String end,
                                 @RequestParam(name="state")Integer state) {
        List<Map> maps = dispatchService.getDispatchMap(limit, page, start, end, state);
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/getDispatchList")
    public String getDispatchList(@RequestParam(name="limit")Integer limit,
                                  @RequestParam(name="page")Integer page,
                                  @RequestParam(name="start")String start,
                                  @RequestParam(name="end")String end,
                                  @RequestParam(name="state")Integer state) {
        List<Map> maps = dispatchService.getDispatchList(limit, page, start, end, state);
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/getDispatchInfo")
    public String getDispatchInfo(@RequestParam(name="id")String id) {
        Map map = dispatchService.getDispatchInfo(id);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/getDispatchLocation")
    public String getDispatchLocation(@RequestParam(name="id")String id) {
        Map map = dispatchService.getDispatchLocation(id);
        return GlobalUtil.PackResponse(map);
    }

    @PostMapping("/addDispatchRecord")
    public String addDispatchRecord() {
        Boolean status = dispatchService.addDispatchRecord();
        return GlobalUtil.PackResponse(status);
    }

    @PostMapping("/setDispatchState")
    public String setDispatchState(@RequestParam(name="id")String id,
                                   @RequestParam(name="state")Integer state,
                                   @RequestParam(name="connState")Integer connState) {
        Boolean status = dispatchService.setDispatchState(id, state, connState);
        return GlobalUtil.PackResponse(status);
    }

    @PostMapping("/lockDispatch")
    public String lockDispatch(@RequestParam(name="id")String id) {
        Boolean status = dispatchService.lockDispatch(id);
        return GlobalUtil.PackResponse(status);
    }

    @PostMapping("/unlockDispatch")
    public String unlockDispatch(@RequestParam(name="id")String id) {
        Boolean status = dispatchService.unlockDispatch(id);
        return GlobalUtil.PackResponse(status);
    }

    @GetMapping("/statisticDispatch")
    public String statisticDispatch(@RequestParam(name="start")String start,
                                    @RequestParam(name="end")String end) {
        List<Map> maps = dispatchService.statisticDispatch(start, end);
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/getTimeSegments")
    public String getTimeSegments() {
        List<Map> maps = dispatchService.getTimeSegments();
        return GlobalUtil.PackResponse(maps);
    }

}
