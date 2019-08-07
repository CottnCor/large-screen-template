package com.largeScreen.api.service;

import java.util.List;
import java.util.Map;

public interface ICommonService {

    List<Map> getRegionByLevel(Short level);

    List<Map> getRegionByParent(String xzqdm);

    List<Map> getRegionByBounds(Short level, String wkt);

    Map getRegionByCoord(Double lng, Double lat);

    Map getRegionByXzqdm(String xzqdm);

    Map getRegionWktByXzqdm(String xzqdm);

    Map getRegionTagByXzqdm(String xzqdm);

    List<Map> getRegionGroups();

    Map getStorageAddress(String id);

    List<Map> getConfigDic(String layerId);

    List<Map> getJzInfoDic(String layerId);

    List<Map> getJctbInfoDic(String layerId);

    Map getJctbInfo(String layerId, String tbbh, String xzqdm);

    List<Map> getVisibleJctb(Double minx, Double miny, Double maxx, Double maxy);

    List<Map> getJctbAffix(String layerId, String tbbh, String xzqdm);

    void addJctbAffix(Map record) throws Exception;

    void editJctbInfo(Map record) throws Exception;

    Map invokeMethod(String method);
}
