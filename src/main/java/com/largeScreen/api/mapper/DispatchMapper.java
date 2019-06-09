package com.largeScreen.api.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DispatchMapper {

    List<Map> selectDispatchOverview();

    List<Map> selectDispatchMap(@Param("limit") Integer limit, @Param("page") Integer page, @Param("start") String start, @Param("end") String end, @Param("state") Integer state);

    List<Map> selectDispatchList(@Param("limit") Integer limit, @Param("page") Integer page, @Param("start") String start, @Param("end") String end, @Param("state") Integer state);

    Map selectDispatchInfo(@Param("id") String id);

    Map selectDispatchLocation(@Param("id") String id);

    Boolean insertDispatchRecord();

    Boolean updateDispatchState(@Param("id") String id, @Param("state") Integer state, @Param("connState") Integer connState);

    Boolean lockDispatch(@Param("id") String id);

    Boolean unlockDispatch(@Param("id") String id);

    Boolean insertMsg();

    List<Map> statisticDispatch(@Param("start") String start, @Param("end") String end);

    List<Map> selectTimeSegments();
}
