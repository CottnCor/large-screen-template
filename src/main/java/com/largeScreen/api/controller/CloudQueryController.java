package com.largeScreen.api.controller;

import com.largeScreen.api.service.ICloudQueryService;
import com.largeScreen.api.util.GlobalUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cloudquery")
public class CloudQueryController {

    @Resource
    private ICloudQueryService cloudQueryService;

    @GetMapping("/getCloudQueryOverview")
    public String getCloudQueryOverview(@RequestParam(name = "startTime", required = true) String startTime, @RequestParam(name = "endTime", required = true) String endTime) {
        Map map = cloudQueryService.getCloudQueryOverview(startTime, endTime);
        return GlobalUtil.PackResponse(map);
    }

    @GetMapping("/getCloudQueryCounts")
    public String getCloudQueryCounts(@RequestParam(name = "startTime", required = true) String startTime, @RequestParam(name = "endTime", required = true) String endTime, @RequestParam(name = "xzqdm", required = false) String xzqdm) {
        List<Map> maps = cloudQueryService.getCloudQueryCounts(startTime, endTime, xzqdm);
        return GlobalUtil.PackResponse(maps);
    }

}