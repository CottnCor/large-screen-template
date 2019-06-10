package com.largeScreen.api.service.impl;

import com.largeScreen.api.annotations.DataSource;
import com.largeScreen.api.aop.DataSourceEnum;
import com.largeScreen.api.mapper.CloudQueryMapper;
import com.largeScreen.api.service.ICloudQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CloudQueryServiceImpl implements ICloudQueryService {

    @Autowired
    private CloudQueryMapper cloudQueryMapper;

    @Override
    @DataSource(DataSourceEnum.ZNFW)
    public Map getCloudQueryOverview(String startTime, String endTime){
        try {
            return cloudQueryMapper.selectCloudQueryOverview(startTime, endTime);
        } catch (Exception ex){
            return null;
        }
    }

    @Override
    @DataSource(DataSourceEnum.ZNFW)
    public List<Map> getCloudQueryCounts(String startTime, String endTime, String xzqdm){
        try {
            Short level = 1;
            if (xzqdm == null || xzqdm.isEmpty()) {
                level = 1;
            } else if ("0000".equals(xzqdm.substring(xzqdm.length() - 4))) {
                level = 2;
            } else if ("00".equals(xzqdm.substring(xzqdm.length() - 2))) {
                level = 3;
            }
            return cloudQueryMapper.selectCloudQueryCounts(startTime, endTime, level, xzqdm);
        } catch (Exception ex){
            return null;
        }
    }

}
