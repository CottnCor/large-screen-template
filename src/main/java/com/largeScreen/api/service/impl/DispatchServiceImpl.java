package com.largeScreen.api.service.impl;

import com.largeScreen.api.mapper.DispatchMapper;
import com.largeScreen.api.service.IDispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DispatchServiceImpl implements IDispatchService {

    @Autowired
    private DispatchMapper dispatchMapper;

    @Override
    public List<Map> getDispatchOverview() {
        try {
            return dispatchMapper.selectDispatchOverview();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Map> getDispatchMap(Integer limit, Integer page, String start, String end, Integer state) {
        try {
            return dispatchMapper.selectDispatchMap(limit, page, start, end, state);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Map> getDispatchList(Integer limit, Integer page, String start, String end, Integer state) {
        try {
            return dispatchMapper.selectDispatchList(limit, page, start, end, state);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Map getDispatchInfo(String id) {
        try {
            return dispatchMapper.selectDispatchInfo(id);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Map getDispatchLocation(String id) {
        try {
            return dispatchMapper.selectDispatchLocation(id);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Boolean addDispatchRecord() {
        try {
            return dispatchMapper.insertDispatchRecord();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Boolean setDispatchState(String id, Integer state, Integer connState) {
        try {
            return dispatchMapper.updateDispatchState(id, state, connState);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Boolean lockDispatch(String id) {
        try {
            return dispatchMapper.lockDispatch(id);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Boolean unlockDispatch(String id) {
        try {
            return dispatchMapper.unlockDispatch(id);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Map> statisticDispatch(String start, String end) {
        try {
            return dispatchMapper.statisticDispatch(start, end);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Map> getTimeSegments() {
        try {
            return dispatchMapper.selectTimeSegments();
        } catch (Exception ex) {
            return null;
        }
    }
}
