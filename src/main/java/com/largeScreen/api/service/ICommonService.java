package com.largeScreen.api.service;

import java.util.List;
import java.util.Map;

public interface ICommonService {

    List<Map> getRegionByLevel(Integer level);

    List<Map> getRegionByParent(String xzqdm);

    List<Map> getRegionByXzqdm(String xzqdm);

    List<Map> getRegionWktByXzqdm(String xzqdm);

    List<Map> getRegionByBounds(Integer level, String wkt);

    List<Map> getRegionTagByXzqdm(String xzqdm);

}
