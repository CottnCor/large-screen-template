package com.largeScreen.api.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CommonMapper {

    List<Map> selectRegionByLevel(@Param("level") Integer level);

    List<Map> selectRegionByParent(@Param("xzqdm") String xzqdm);

    List<Map> selectRegionByXzqdm(@Param("xzqdm") String code);

    List<Map> selectRegionWktByXzqdm(@Param("xzqdm") String code);

    List<Map> selectRegionByBounds(@Param("level") Integer level, @Param("wkt") String wkt);

    List<Map> selectRegionTagByXzqdm(@Param("xzqdm") String code);
}
