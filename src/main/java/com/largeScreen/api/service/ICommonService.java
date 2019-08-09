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

    Map getOssConfig();

    Map getStorageAddress(String id);

    List<Map> getEnumeratorDic(String key);

    List<Map> getConfigDic(String layerId, String type, Integer pid, Integer subtype);

    List<Map> getSupervisedInfoDic(Integer level, String layerId);

    List<Map> getJzInfoDic(String layerId);

    List<Map> getJctbInfoDic(String layerId);

    Map getJctbInfo(String layerId, String tbbh, String xzqdm, List<String> fields);

    List<Map> getVisibleJctb(Double minx, Double miny, Double maxx, Double maxy);

    List<Map> getJctbAffix(String layerId, String tbbh, String xzqdm);

    void addJctbAffix(String layerId, String tbbh, String xzqdm, List<String> fields, List<String> values) throws Exception;

    void editJctbInfo(String layerId, String tbbh, String xzqdm, List<String> fields, List<String> values) throws Exception;

    Map invokeMethod(String method);
}
