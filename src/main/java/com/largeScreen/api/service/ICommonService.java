package com.largeScreen.api.service;

import java.util.List;
import java.util.Map;

public interface ICommonService {

    List<Map> getRegionByLevel(Short level);

    List<Map> getRegionByParent(String xzqdm);

    List<Map> getRegionByBounds(Short level, String wkt);

    Map getRegionByXzqdm(String xzqdm);

    Map getRegionWktByXzqdm(String xzqdm);

    Map getRegionTagByXzqdm(String xzqdm);

    List<Map> getRegionGroups();

    Map getStorageAddress(String id);

    Map getJctbInfo(String layerId, String jctbId);

    List<Map> getVisibleJctb(Double minx, Double miny, Double maxx, Double maxy);

    List<Map> getJctbAffix(String layerId, String jctbId);

    void editJctbInfo(Map record);
}
