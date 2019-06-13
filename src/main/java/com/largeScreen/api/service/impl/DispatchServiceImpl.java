package com.largeScreen.api.service.impl;

import com.largeScreen.api.annotations.DataSource;
import com.largeScreen.api.aop.DataSourceEnum;
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
    @DataSource(DataSourceEnum.ZXJZ)
    public List<Map> getObjectType(){
        try {
            return dispatchMapper.selectObjectType();
        } catch (Exception ex){
            return null;
        }
    }

    @Override
    @DataSource(DataSourceEnum.ZXJZ)
    public Map getOrderDate(String bizId, String date){
        try {
            return dispatchMapper.selectOrderDate(bizId, date);
        } catch (Exception ex){
            return null;
        }
    }

    @Override
    @DataSource(DataSourceEnum.ZXJZ)
    public List<Map> getTimeSegments(String dateId){
        try {
            return dispatchMapper.selectTimeSegments(dateId);
        } catch (Exception ex){
            return null;
        }
    }

    @Override
    @DataSource(DataSourceEnum.ZXJZ)
    public Map getDispatchOverview(String bizId){
        try {
            return dispatchMapper.selectDispatchOverview(bizId);
        } catch (Exception ex){
            return null;
        }
    }

    @Override
    @DataSource(DataSourceEnum.ZXJZ)
    public List<Map> getDispatchRecord(Short limit, Short page){
        try {
            return dispatchMapper.selectDispatchRecord(limit, page);
        } catch (Exception ex){
            return null;
        }
    }

    @Override
    @DataSource(DataSourceEnum.ZXJZ)
    public List<Map> getDispatchList(Short state, String segmentId, Short limit, Short page){
        try {
            return dispatchMapper.selectDispatchList(state, segmentId, limit, page);
        } catch (Exception ex){
            return null;
        }
    }

    @Override
    @DataSource(DataSourceEnum.ZXJZ)
    public List<Map> getDispatchCoords(String id){
        try {
            return dispatchMapper.selectDispatchCoords(id);
        } catch (Exception ex){
            return null;
        }
    }

    @Override
    @DataSource(DataSourceEnum.ZXJZ)
    public void addDispatchRecord(Map record) throws Exception{
        dispatchMapper.insertDispatchRecord(record);
    }

    @Override
    @DataSource(DataSourceEnum.ZXJZ)
    public void editDispatchState(String id, Short state, Short connState) throws Exception{
        dispatchMapper.updateDispatchState(id, state, connState);
    }

    @Override
    @DataSource(DataSourceEnum.ZXJZ)
    public void lockDispatch(String id) throws Exception{
        dispatchMapper.lockDispatch(id);
    }

    @Override
    @DataSource(DataSourceEnum.ZXJZ)
    public void unlockDispatch(String id) throws Exception{
        dispatchMapper.unlockDispatch(id);
    }
}
