package com.largeScreen.api.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CommonMapper {

    List<Map> selectRegionByLevel(@Param("level") Short level);

    List<Map> selectRegionByParent(@Param("xzqdm") String xzqdm);

    List<Map> selectRegionByBounds(@Param("level") Short level, @Param("wkt") String wkt);

    Map selectRegionByCoord(@Param("lng") Double lng, @Param("lat") Double lat);

    Map selectRegionByXzqdm(@Param("xzqdm") String xzqdm);

    Map selectRegionWktByXzqdm(@Param("xzqdm") String xzqdm);

    Map selectRegionTagByXzqdm(@Param("xzqdm") String xzqdm);

    Map selectOssConfig();

    Map selectStorageAddress(@Param("id") String xzqdm);

    List<Map> selectEnumeratorDic(@Param("key") String key);

    List<Map> selectConfigDic(@Param("layerId") String layerId, @Param("type") String type, @Param("pid") Integer pid, @Param("subtype") Integer subtype);

    Map selectJctbInfo(@Param("fields") List<String> fields, @Param("layerId") String layerId, @Param("tbbh") String tbbh, @Param("xzqdm") String xzqdm);

    List<Map> selectVisibleJctb(@Param("minx") Double minx, @Param("miny") Double miny, @Param("maxx") Double maxx, @Param("maxy") Double maxy, @Param("layerId") String layerId);

    List<Map> selectJctbAffix(@Param("layerId") String layerId, @Param("tbbh") String tbbh, @Param("xzqdm") String xzqdm);

    void insertJctbAffix(@Param("record") Map record);

    void updateJctbInfo(@Param("record") Map record);

    Map invokeMethod(@Param("method") String method);
}
