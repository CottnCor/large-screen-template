package com.largeScreen.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.largeScreen.api.service.IDispatchService;
import com.largeScreen.api.util.GlobalUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/dispatch", produces = { "application/json;charset=UTF-8" })
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
        List<Map> maps = dispatchService.getDispatchOverview(bizId);
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/getDispatchRecord")
    public String getDispatchRecord(@RequestParam(name="limit")Short limit, @RequestParam(name="page")Short page){
        List<Map> maps = dispatchService.getDispatchRecord(limit, page);
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/getDispatchList")
    public String getDispatchList(@RequestParam(name="filter")String filter) throws UnsupportedEncodingException {
        filter = URLDecoder.decode(filter, "utf-8");
        Short page = JSON.parseObject(filter).getShort("page");
        Short limit = JSON.parseObject(filter).getShort("limit");
        List<String> segmentId = JSON.parseObject(filter).getJSONArray("segmentId").toJavaList(String.class);
        List<Short> state = JSON.parseObject(filter).getJSONArray("state").toJavaList(Short.class);
        List<Map> maps = dispatchService.getDispatchList(state, segmentId, limit, page);
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/getDispatchCoords")
    public String getDispatchCoords(@RequestParam(name="id")String id){
        List<Map> maps = dispatchService.getDispatchCoords(id);
        return GlobalUtil.PackResponse(maps);
    }

    @GetMapping("/getDispatchInfo")
    public String getDispatchInfo(@RequestParam(name="id")String id){
        Map map = dispatchService.getDispatchInfo(id);
        return GlobalUtil.PackResponse(map);           
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
