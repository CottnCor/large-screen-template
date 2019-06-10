package com.largeScreen.api.service;

import java.util.List;
import java.util.Map;

public interface ICloudQueryService {

    Map getCloudQueryOverview(String startTime, String endTime);

    List<Map> getCloudQueryCounts(String startTime, String endTime, String xzqdm);

}
