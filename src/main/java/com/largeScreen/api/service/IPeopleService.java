package com.largeScreen.api.service;

import java.util.List;
import java.util.Map;

public interface IPeopleService {

    Map getPeopleOverview(String type);

    List<Map> getLevelCounts(String type);

    List<Map> getPeopleCounts(String type, String xzqdm);

    List<Map> getUserTree();

    Map getYxInfo(Long userid);

    Map getUserInfo(Long userid);

    Map getUserState(Long userid);

    Map getUserCoords(Long userid);

    List<Map> getPeopleCoords(Short level, String bounds);

    Boolean sendMsg(Long userid, String msg);
}
