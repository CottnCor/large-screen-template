package com.largeScreen.api.controller;

import com.alibaba.fastjson.JSONObject;
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

    @GetMapping("/getObjectType")
    public String getObjectType(){
        List<Map> maps = dispatchService.getObjectType();
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/getOrderDate")
    public String getOrderDate(@RequestParam(name="bizId")String bizId, @RequestParam(name="date")String date){
        Map map = dispatchService.getOrderDate(bizId, date);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/getTimeSegments")
    public String getTimeSegments(@RequestParam(name="dateId")String dateId){
        List<Map> maps = dispatchService.getTimeSegments(dateId);
        return GlobalUtil.PackResponse(maps);
    }
    
    @GetMapping("/getDispatchOverview")
    public String getDispatchOverview(@RequestParam(name="bizId")String bizId){
        Map map = dispatchService.getDispatchOverview(bizId);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/getDispatchRecord")
    public String getDispatchRecord(@RequestParam(name="limit")Short limit, @RequestParam(name="page")Short page){
        List<Map> maps = dispatchService.getDispatchRecord(limit, page);
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/getDispatchList")
    public String getDispatchList(@RequestParam(name="state")Short state, @RequestParam(name="segmentId")String segmentId, @RequestParam(name="limit")Short limit, @RequestParam(name="page")Short page){
        List<Map> maps = dispatchService.getDispatchList(state, segmentId, limit, page);
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/getDispatchCoords")
    public String getDispatchCoords(@RequestParam(name="id")String id){
        List<Map> maps = dispatchService.getDispatchCoords(id);
        return GlobalUtil.PackResponse(maps);
    }

    @PostMapping("/addDispatchRecord")
    public void addDispatchRecord(@RequestParam(name="record")String record) throws Exception{
        dispatchService.addDispatchRecord((Map) JSONObject.parse(record));
    }

    @PostMapping("/editDispatchState")
    public void editDispatchState(@RequestParam(name="id")String id, @RequestParam(name="state")Short state, @RequestParam(name="connState")Short connState) throws Exception{
        dispatchService.editDispatchState(id, state, connState);
    }

    @PostMapping("/lockDispatch")
    public void lockDispatch(@RequestParam(name="id")String id) throws Exception{
        dispatchService.lockDispatch(id);
    }

    @PostMapping("/unlockDispatch")
    public void unlockDispatch(@RequestParam(name="id")String id) throws Exception{
        dispatchService.unlockDispatch(id);
    }

}
