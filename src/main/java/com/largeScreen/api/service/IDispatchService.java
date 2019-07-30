package com.largeScreen.api.service;

import java.util.List;
import java.util.Map;

public interface IDispatchService {

    List<Map> getObjectType();

    Map getOrderDate(String bizId, String date);

    List<Map> getTimeSegments(String dateId);

    List<Map> getDispatchOverview(String bizId);

    List<Map> getDispatchRecord(Short limit, Short page);

    List<Map> getDispatchList(List<Short> state, String segmentId, Short limit, Short page);

    List<Map> getDispatchCoords(String id);

    void addDispatchRecord(Map record) throws Exception;

    void editDispatchState(String id, Short state, Short connState) throws Exception;

    void lockDispatch(String id) throws Exception;

    void unlockDispatch(String id) throws Exception;
}
