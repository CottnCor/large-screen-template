package com.largeScreen.api.service.impl;

import com.largeScreen.api.aop.DataSourceEnum;
import com.largeScreen.api.mapper.CommonMapper;
import com.largeScreen.api.annotations.DataSource;
import com.largeScreen.api.service.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommonServiceImpl implements ICommonService {

    @Autowired
    private CommonMapper commonMapper;

    @Override
    @DataSource(DataSourceEnum.ZXJZ)
    public List<Map> getRegionByLevel(Integer level) {
        try {
            return commonMapper.selectRegionByLevel(level);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Map> getRegionByParent(String xzqdm) {
        try {
            return commonMapper.selectRegionByParent(xzqdm);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Map> getRegionByBounds(Integer level, String wkt) {
        try {
            return commonMapper.selectRegionByBounds(level, wkt);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Map getRegionByXzqdm(String xzqdm) {
        try {
            return commonMapper.selectRegionByXzqdm(xzqdm);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Map getRegionWktByXzqdm(String xzqdm) {
        try {
            return commonMapper.selectRegionWktByXzqdm(xzqdm);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Map getRegionTagByXzqdm(String xzqdm) {
        try {
            return commonMapper.selectRegionTagByXzqdm(xzqdm);
        } catch (Exception ex) {
            return null;
        }
    }
}
