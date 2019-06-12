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

    Map selectRegionByXzqdm(@Param("xzqdm") String xzqdm);

    Map selectRegionWktByXzqdm(@Param("xzqdm") String xzqdm);

    Map selectRegionTagByXzqdm(@Param("xzqdm") String xzqdm);

    Map selectStorageAddress(@Param("id") String xzqdm);

    Map selectJctbInfo(@Param("layerId") String layerId, @Param("jctbId") String jctbId);

    List<Map> selectVisibleJctb(@Param("minx") Double minx, @Param("miny") Double miny, @Param("maxx") Double maxx, @Param("maxy") Double maxy, @Param("layerId") String layerId);

    List<Map> selectJctbAffix(@Param("layerId") String layerId, @Param("jctbId") String jctbId);

    void updateJctbInfo(@Param("record") Map record);
}
