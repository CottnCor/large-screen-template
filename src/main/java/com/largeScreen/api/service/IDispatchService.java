package com.largeScreen.api.service;

import java.util.List;
import java.util.Map;

public interface IDispatchService {

    List<Map> getDispatchOverview();

    List<Map> getDispatchMap(Integer limit, Integer page, String start, String end, Integer state);

    List<Map> getDispatchList(Integer limit, Integer page, String start, String end, Integer state);

    Map getDispatchInfo(String id);

    Map getDispatchLocation(String id);

    Boolean addDispatchRecord();

    Boolean setDispatchState(String id, Integer state, Integer connState);

    Boolean lockDispatch(String id);

    Boolean unlockDispatch(String id);

    List<Map> statisticDispatch(String start, String end);

    List<Map> getTimeSegments();
}
