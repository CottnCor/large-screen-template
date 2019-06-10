package com.largeScreen.api.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CloudQueryMapper {

    Map selectCloudQueryOverview(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<Map> selectCloudQueryCounts(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("level") Short level, @Param("xzqdm") String xzqdm);

}
